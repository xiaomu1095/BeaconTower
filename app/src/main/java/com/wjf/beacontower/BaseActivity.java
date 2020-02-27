package com.wjf.beacontower;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
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

    final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance


    final int TIPS_LENGTH_SHORT = 1000;
    final int TIPS_LENGTH_LONG = 1800;

    //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框
    private static String BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    protected AMapLocationClient locationClient = null;
    /**
     * 需要进行检测的权限数组
     */
    protected String[] locationPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT > 28
                && getApplicationContext().getApplicationInfo().targetSdkVersion > 28) {
            locationPermissions = new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    BACKGROUND_LOCATION_PERMISSION
            };
        }
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
        return rxPermissions
                .requestEachCombined(locationPermissions)
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


    protected void initLocationClient() {
        if (locationClient == null) {
            locationClient = new AMapLocationClient(getApplicationContext());
        }
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setHttpTimeOut(20000);//用于设定通过网络定位获取结果的超时时间，毫秒级
        /*
         * 设置签到场景，相当于设置为：
         * option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
         * option.setOnceLocation(true);
         * option.setOnceLocationLatest(true);
         * option.setMockEnable(false);
         * option.setWifiScan(true);
         * option.setGpsFirst(false);
         * 其他属性均为模式属性。
         * 如果要改变其中的属性，请在在设置定位场景之后进行
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        locationClient.setLocationOption(option);
        //设置定位监听
//        locationClient.setLocationListener(this);
    }

    protected void destroyLocationClient() {
        //销毁时，需要销毁定位client
        if (null != locationClient) {
            locationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            locationClient.onDestroy();
        }
    }

}
