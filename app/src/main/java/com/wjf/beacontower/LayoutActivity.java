package com.wjf.beacontower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_power_supply_name_v, tv_power_supply_name_v3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        tv_power_supply_name_v = findViewById(R.id.tv_power_supply_name_v);
        tv_power_supply_name_v.setOnClickListener(this);
        tv_power_supply_name_v3 = findViewById(R.id.tv_power_supply_name_v3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_power_supply_name_v:
                tv_power_supply_name_v3.setVisibility(View.VISIBLE);
                break;
        }
    }
}
