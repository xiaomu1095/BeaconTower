package com.wjf.beacontower;

import android.app.assist.AssistContent;
import android.content.Context;
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
            tv_power_supply_name_v.setText(supplyName);
        }
        String transfer = allValues.get(ConstantValues.MAP_KEY_TRANSFORMER);
        if (!TextUtils.isEmpty(transfer)) {
            tv_transformer_name_v.setText(transfer);
        }
        String lineName = allValues.get(ConstantValues.MAP_KEY_LINE_NAME);
        if (!TextUtils.isEmpty(lineName)) {
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
                break;
        }
    }

    private void selectSupplyDialog() {
        XMLPowerSupplyData powerSupplyData = new XMLPowerSupplyData();
        String[] supplyDatas = powerSupplyData.getmPowerSupplyDatas();
        if (supplyDatas == null) {
            AssetManager assetManager = getAssets();
            try {
                InputStream inputStream = assetManager.open(ConstantValues.POWER_SUPPLY_XML_NAME);
                powerSupplyData.initProvinceDatas(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final String[] items = supplyDatas;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_power_supply_name_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void selectTransformerDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_SETUP;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_transformer_name_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void selectLineDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_SETUP;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_line_name_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private Context getActivity() {
        return this;
    }


}
