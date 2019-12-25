package com.wjf.beacontower;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_power_supply_name, et_line_name, et_line_duty, et_contact_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 点击确认基础信息
        findViewById(R.id.btn_confirm_base_info).setOnClickListener(this);

        et_power_supply_name = findViewById(R.id.et_power_supply_name);
        et_line_name = findViewById(R.id.et_line_name);
        et_line_duty = findViewById(R.id.et_line_duty);
        et_contact_info = findViewById(R.id.et_contact_info);

        initValues();
    }

    private void initValues() {
        Map<String, String> allValues = SpUtil.getAllApValues(this);
        String contact = allValues.get(ConstantValues.MAP_KEY_CONTACT);
        if (!TextUtils.isEmpty(contact)) {
            et_contact_info.setText(contact);
        }
        String lineName = allValues.get(ConstantValues.MAP_KEY_LINE_NAME);
        if (!TextUtils.isEmpty(lineName)) {
            et_line_name.setText(lineName);
        }
        String lineDuty = allValues.get(ConstantValues.MAP_KEY_LINE_DUTY);
        if (!TextUtils.isEmpty(lineDuty)) {
            et_line_duty.setText(lineDuty);
        }
        String supplyName = allValues.get(ConstantValues.MAP_KEY_SUPPLY);
        if (!TextUtils.isEmpty(supplyName)) {
            et_power_supply_name.setText(supplyName);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirm_base_info) {
            String contactInfo = et_contact_info.getText().toString();
            String lineDuty = et_line_duty.getText().toString();
            String lineName = et_line_name.getText().toString();
            String powerSupplyName = et_power_supply_name.getText().toString();
            Map<String, String> map = new HashMap<>();
            map.put(ConstantValues.MAP_KEY_CONTACT, contactInfo);
            map.put(ConstantValues.MAP_KEY_LINE_DUTY, lineDuty);
            map.put(ConstantValues.MAP_KEY_LINE_NAME, lineName);
            map.put(ConstantValues.MAP_KEY_SUPPLY, powerSupplyName);
            SpUtil.putString(this, map);

            Intent intent = new Intent();
            intent.setClass(this, InfoCollectionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.MAP_KEY_CONTACT, contactInfo);
            bundle.putString(ConstantValues.MAP_KEY_LINE_DUTY, lineDuty);
            bundle.putString(ConstantValues.MAP_KEY_LINE_NAME, lineName);
            bundle.putString(ConstantValues.MAP_KEY_SUPPLY, powerSupplyName);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


}
