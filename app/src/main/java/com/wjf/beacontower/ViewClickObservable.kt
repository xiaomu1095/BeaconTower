@file:JvmName("RxView")
@file:JvmMultifileClass

package com.wjf.beacontower

import android.os.Looper
import android.view.View
import androidx.annotation.CheckResult
import androidx.annotation.RestrictTo
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.Disposables

/**
 * @author xiaom
 * Time: 2020/3/5 10:22
 * Author: Lin.Li
 * Description:
 */
@CheckResult
fun View.clicks(): Observable<Unit> {
    return ViewClickObservable(this)
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun checkMainThread(observer: Observer<*>): Boolean {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        observer.onSubscribe(Disposables.empty())
        observer.onError(IllegalStateException(
                "Expected to be called on the main thread but was " + Thread.currentThread().name))
        return false
    }
    return true
}

private class ViewClickObservable(
        private val view: View
) : Observable<Unit>() {
    override fun subscribeActual(observer: Observer<in Unit>) {
        if (!checkMainThread(observer)) {
            return
        }
        val listener = Listener(view, observer)
        observer.onSubscribe(listener)
        view.setOnClickListener(listener)
    }

    private class Listener(
            private val view: View,
            private val observer: Observer<in Unit>
    ) : MainThreadDisposable(), View.OnClickListener {


        override fun onClick(v: View) {
            if (!isDisposed) {
                observer.onNext(Unit)
            }
        }

        override fun onDispose() {
            view.setOnClickListener(null)
        }
    }

}
