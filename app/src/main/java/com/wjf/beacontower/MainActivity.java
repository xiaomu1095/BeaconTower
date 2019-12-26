package com.wjf.beacontower;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.wjf.beacontower.util.XMLPowerSupplyData;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * XML里面的供电所-变电站-线路信息解析类
     */
    private XMLPowerSupplyData powerSupplyData;
    private String mCurrentSupplyName;
    private String mCurrentTransformerName;
    private String mCurrentLineName;

    private EditText et_line_duty, et_contact_info;
    private TextView tv_line_name_v, tv_transformer_name_v, tv_power_supply_name_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 点击确认基础信息
        findViewById(R.id.btn_confirm_base_info).setOnClickListener(this);

        et_line_duty = findViewById(R.id.et_line_duty);
        et_contact_info = findViewById(R.id.et_contact_info);

        tv_power_supply_name_v = findViewById(R.id.tv_power_supply_name_v);
        tv_power_supply_name_v.setOnClickListener(this);
        tv_transformer_name_v = findViewById(R.id.tv_transformer_name_v);
        tv_transformer_name_v.setOnClickListener(this);
        tv_line_name_v = findViewById(R.id.tv_line_name_v);
        tv_line_name_v.setOnClickListener(this);

        initValues();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_power_supply_name_v:
                selectSupplyDialog();
                break;
            case R.id.tv_transformer_name_v:
                selectTransformerDialog();
                break;
            case R.id.tv_line_name_v:
                selectLineDialog();
                break;
            case R.id.btn_confirm_base_info:
                confirmBaseInfo();
                break;
        }
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
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCurrentSupplyName = items[which];
                        mCurrentTransformerName = null;
                        mCurrentLineName = null;
                        tv_power_supply_name_v.setText(mCurrentSupplyName);
                        tv_transformer_name_v.setText(null);
                        tv_line_name_v.setText(null);
                        dialog.dismiss();
                    }
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
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCurrentTransformerName = items[which];
                        mCurrentLineName = null;
                        tv_transformer_name_v.setText(mCurrentTransformerName);
                        tv_line_name_v.setText(null);
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    // 选择线路对话框
    private void selectLineDialog() {
        if (!initPowerSupplyData()){
            return;
        }
        Map<String, String[]> lineDatasMap = powerSupplyData.getmLineDatasMap();
        final String[] items = lineDatasMap.get(mCurrentTransformerName);
        if (items == null) {
            return;
        }
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCurrentLineName = items[which];
                        tv_line_name_v.setText(mCurrentLineName);
                        dialog.dismiss();
                    }
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
        String lineDuty = et_line_duty.getText().toString();
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

        Intent intent = new Intent();
        intent.setClass(this, InfoCollectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValues.MAP_KEY_SUPPLY, powerSupplyName);
        bundle.putString(ConstantValues.MAP_KEY_TRANSFORMER, transformerName);
        bundle.putString(ConstantValues.MAP_KEY_LINE_NAME, lineName);
        bundle.putString(ConstantValues.MAP_KEY_LINE_DUTY, lineDuty);
        bundle.putString(ConstantValues.MAP_KEY_CONTACT, contactInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
