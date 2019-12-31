package com.wjf.beacontower;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wjf.beacontower.util.FileIOUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void writeStringToFile(String content) {
        File info = getExternalFilesDir(ConstantValues.COLLECTION_INFO_PATH_NAME);
        if (info != null) {
            String path = info.getAbsolutePath() + File.separatorChar + ConstantValues.COLLECTION_INFO_TXT_NAME;
            FileIOUtils.writeFileFromStringWithTime(path,content,true);
        }
    }

    protected Observable<List<Permission>> grantPermissions() {
        return getNotGrantedPermissions()
                .doOnNext(permissions -> {
                    // 权限是否通过
                    if (permissions.size() > 0) {
                        new QMUIDialog.MessageDialogBuilder(getApplicationContext())
                                .setMessage("权限不足！请授权！")
                                .setTitle("授权提醒")
                                .addAction("确定", (dialog, index) -> dialog.dismiss())
                                .create().show();
                    }
                });
    }

    // 请求所有的权限
    protected Observable<List<Permission>> getNotGrantedPermissions() {
        List<Permission> permissionList = new ArrayList<>();
        RxPermissions rxPermissions = new RxPermissions(this);
        return rxPermissions
                .requestEachCombined(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .flatMap((Function<Permission, ObservableSource<List<Permission>>>) permission ->
                        Observable.create(emitter -> {
                            if (!permission.granted) {
                                permissionList.add(permission);
                            }
                            emitter.onNext(permissionList);
                        }))
                .onExceptionResumeNext(observer -> observer.onNext(new ArrayList<>()))
                ;
    }

    // 重新授权或者退出应用对话Dialog
//    private Observable<AppStartInfo> grantPermissionDialogObservable() {
//        return Observable
//                .create(new ObservableOnSubscribe<AppStartInfo>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<AppStartInfo> emitter) throws Exception {
//                        if (emitter.isDisposed()) {return;}
//                        HeheDialog.MessageDialogBuilder builder = new HeheDialog.MessageDialogBuilder(MainActivity.this);
//                        builder.setTitle("提示！！！");
//                        builder.setMessage("如果拒绝授权，程序将无法正常使用，是否重新授权或者退出应用？");
//                        builder.addAction("重新授权", (dialog, index) -> {
//                            dialog.dismiss();
//                            AppStartInfo appStartInfo = new AppStartInfo();
//                            appStartInfo.setReGrantedPermission(Boolean.TRUE);
//                            emitter.onNext(appStartInfo);
//                        });
//                        builder.addAction("退出应用", (dialog, index) -> {
//                            emitter.onComplete();
//                            dialog.dismiss();
//                            MainActivity.this.finish();
//                        });
//                        if (dialog != null) { dialog.cancel(); }
//                        dialog = builder.create();
//                        dialog.setCancelable(false);
//                        dialog.setCanceledOnTouchOutside(false);
//                        dialog.show();
//                    }
//                });
//    }


}
