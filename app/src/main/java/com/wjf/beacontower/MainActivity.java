package com.wjf.beacontower;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.wjf.beacontower.db.TowerDatabase;
import com.wjf.beacontower.db.TowerRegisterDAO;
import com.wjf.beacontower.db.TowerRegisterDO;
import com.wjf.beacontower.model.TowerRegisterInfo;
import com.wjf.beacontower.util.SpUtil;
import com.wjf.beacontower.util.XMLPowerSupplyData;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    /**
     * XML里面的供电所-变电站-线路信息解析类
     */
    private XMLPowerSupplyData powerSupplyData;
    private String mCurrentSupplyName;
    private String mCurrentTransformerName;
    private String mCurrentLineName;

    @BindView(R.id.et_line_duty) EditText et_line_duty;
    @BindView(R.id.et_contact_info) EditText et_contact_info;
    @BindView(R.id.tv_line_name_v) TextView tv_line_name_v;
    @BindView(R.id.tv_transformer_name_v) TextView tv_transformer_name_v;
    @BindView(R.id.tv_power_supply_name_v) TextView tv_power_supply_name_v;
    @BindView(R.id.btn_confirm_base_info) Button btn_confirm_base_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initValues();
        initEvent();
    }

    private void initValues() {
        Map<String, String> allValues = SpUtil.getAllApValues(this);
        String supplyName = allValues.get(ConstantValues.MAP_KEY_SUPPLY);
        if (!TextUtils.isEmpty(supplyName)) {
            mCurrentSupplyName = supplyName;
            tv_power_supply_name_v.setText(supplyName);
        }
        String transfer = allValues.get(ConstantValues.MAP_KEY_TRANSFORMER);
        if (!TextUtils.isEmpty(transfer)) {
            mCurrentTransformerName = transfer;
            tv_transformer_name_v.setText(transfer);
        }
        String lineName = allValues.get(ConstantValues.MAP_KEY_LINE_NAME);
        if (!TextUtils.isEmpty(lineName)) {
            mCurrentLineName = lineName;
            tv_line_name_v.setText(lineName);
        }
        String lineDuty = allValues.get(ConstantValues.MAP_KEY_LINE_DUTY);
        if (!TextUtils.isEmpty(lineDuty)) {
            et_line_duty.setText(lineDuty);
        }
        String contact = allValues.get(ConstantValues.MAP_KEY_CONTACT);
        if (!TextUtils.isEmpty(contact)) {
            et_contact_info.setText(contact);
        }
    }

    private void initEvent() {
        RxView.clicks(btn_confirm_base_info)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(unit -> confirmBaseInfo());

        RxView.clicks(tv_power_supply_name_v)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(unit -> selectSupplyDialog());
        RxView.clicks(tv_transformer_name_v)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(unit -> selectTransformerDialog());
        RxView.clicks(tv_line_name_v)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(unit -> selectLineDialog());
    }

    // 初始化供电所信息
    private boolean initPowerSupplyData(){
        if (powerSupplyData != null && powerSupplyData.getmPowerSupplyDatas() != null) {
            return true;
        }
        this.powerSupplyData = new XMLPowerSupplyData();
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open(ConstantValues.POWER_SUPPLY_XML_NAME);
            this.powerSupplyData.initAllPowerSupplyData(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.powerSupplyData.getmPowerSupplyDatas() != null;
    }

    // 选择供电所信息对话框
    private void selectSupplyDialog() {
        if (!initPowerSupplyData()){
            return;
        }
        final String[] items = powerSupplyData.getmPowerSupplyDatas();
        if (items == null) {
            return;
        }
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    mCurrentSupplyName = items[which];
                    mCurrentTransformerName = null;
                    mCurrentLineName = null;
                    tv_power_supply_name_v.setText(mCurrentSupplyName);
                    tv_transformer_name_v.setText(null);
                    tv_line_name_v.setText(null);
                    dialog.dismiss();
                })
                .create().show();
    }

    // 选择变电站信息对话框
    private void selectTransformerDialog() {
        if (!initPowerSupplyData()){
            return;
        }
        Map<String, String[]> transformerDataMap = powerSupplyData.getmTransformerDatasMap();
        final String[] items = transformerDataMap.get(mCurrentSupplyName);
        if (items == null) {
            return;
        }
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    mCurrentTransformerName = items[which];
                    mCurrentLineName = null;
                    tv_transformer_name_v.setText(mCurrentTransformerName);
                    tv_line_name_v.setText(null);
                    dialog.dismiss();
                })
                .create().show();
    }

    // 选择线路对话框
    private void selectLineDialog() {
        if (!initPowerSupplyData()){
            return;
        }
        Map<String, String[]> lineDatasMap = powerSupplyData.getmLineDatasMap();
        final String[] items = lineDatasMap.get(mCurrentSupplyName + mCurrentTransformerName);
        if (items == null) {
            return;
        }
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    mCurrentLineName = items[which];
                    tv_line_name_v.setText(mCurrentLineName);
                    dialog.dismiss();
                })
                .create().show();
    }

    // 确认基础信息
    private void confirmBaseInfo(){
        if (mCurrentSupplyName == null || mCurrentTransformerName == null || mCurrentLineName == null) {
            Toast.makeText(this, "请选择线路信息！！", Toast.LENGTH_SHORT).show();
            return;
        }
        String contactInfo = et_contact_info.getText().toString();
        if (TextUtils.isEmpty(contactInfo)) {
            Toast.makeText(this, "请输入联系方式！", Toast.LENGTH_SHORT).show();
            return;
        }
        String lineDuty = et_line_duty.getText().toString();
        if (TextUtils.isEmpty(lineDuty)) {
            Toast.makeText(this, "请输入线路专责！", Toast.LENGTH_SHORT).show();
            return;
        }
        String powerSupplyName = tv_power_supply_name_v.getText().toString();
        String transformerName = tv_transformer_name_v.getText().toString();
        String lineName = tv_line_name_v.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put(ConstantValues.MAP_KEY_SUPPLY, powerSupplyName);
        map.put(ConstantValues.MAP_KEY_TRANSFORMER, transformerName);
        map.put(ConstantValues.MAP_KEY_LINE_NAME, lineName);
        map.put(ConstantValues.MAP_KEY_LINE_DUTY, lineDuty);
        map.put(ConstantValues.MAP_KEY_CONTACT, contactInfo);
        SpUtil.putString(this, map);

        TowerRegisterInfo towerRegisterInfo = new TowerRegisterInfo();
        towerRegisterInfo.setSupplyName(powerSupplyName);
        towerRegisterInfo.setTransformerName(transformerName);
        towerRegisterInfo.setLineName(lineName);
        towerRegisterInfo.setLineDuty(lineDuty);
        towerRegisterInfo.setContactInfo(contactInfo);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValues.BUNDLE_KEY_BASE_INFO, towerRegisterInfo);
        Intent intent = new Intent();
        intent.setClass(this, InfoCollectionActivity.class);
        intent.putExtras(bundle);
//        startActivity(intent);
        TowerDatabase database = TowerDatabase.getInstance(this);
        TowerRegisterDAO towerRegisterDAO = database.towerRegisterDAO();
        TowerRegisterDO towerRegister = new TowerRegisterDO();
        towerRegister.setSupplyName(powerSupplyName);
        towerRegister.setTransformerName(transformerName);
        towerRegister.setLineName(lineName);
        towerRegister.setLineDuty(lineDuty);
        towerRegister.setContactInfo(contactInfo);
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String dateFormat = sdf.format(new Date());
        towerRegister.setCreateTime(dateFormat);

        towerRegisterDAO.insertNewOne(towerRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BiConsumer<Long, Throwable>() {
                    @Override
                    public void accept(Long aLong, Throwable throwable) throws Exception {
                        Log.i("MainActivity", aLong + "=============");
                    }
                });

        towerRegisterDAO.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BiConsumer<List<TowerRegisterDO>, Throwable>() {
                    @Override
                    public void accept(List<TowerRegisterDO> towerRegisterDOS, Throwable throwable) throws Exception {
                        for (TowerRegisterDO t: towerRegisterDOS) {
                            Log.i("MainActivity", "-------------" + t.getSupplyName());
                        }
                    }
                });
    }

}
