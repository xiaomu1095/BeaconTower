package com.wjf.beacontower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm_base_info:
                String contactInfo = et_contact_info.getText().toString();
                if (!TextUtils.isEmpty(contactInfo)) {
                    Toast.makeText(this, contactInfo, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "empty!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return;
        }
    }


}
