package com.wjf.beacontower;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class InfoCollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private TextView tv_line_type_v, tv_tower_texture_v, tv_tower_use_v, tv_tower_location_v,
            tv_tower_setup_v, tv_tower_terrain_v, tv_commissioning_date_v;
    private EditText et_tower_height_v, et_wire_type_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);
        initToolbar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_line_type_v = findViewById(R.id.tv_line_type_v);
        tv_line_type_v.setOnClickListener(this);
        tv_tower_texture_v = findViewById(R.id.tv_tower_texture_v);
        tv_tower_texture_v.setOnClickListener(this);
        tv_tower_use_v = findViewById(R.id.tv_tower_use_v);
        tv_tower_use_v.setOnClickListener(this);
        tv_tower_location_v = findViewById(R.id.tv_tower_location_v);
        tv_tower_location_v.setOnClickListener(this);
        et_tower_height_v = findViewById(R.id.et_tower_height_v);
        tv_tower_setup_v = findViewById(R.id.tv_tower_setup_v);
        tv_tower_setup_v.setOnClickListener(this);
        et_wire_type_v = findViewById(R.id.et_wire_type_v);

        tv_tower_terrain_v = findViewById(R.id.tv_tower_terrain_v);
        tv_tower_terrain_v.setOnClickListener(this);
        tv_commissioning_date_v = findViewById(R.id.tv_commissioning_date_v);
        tv_commissioning_date_v.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.collect_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_history:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setTitle("信息登记");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_line_type_v:
                selectLineTypeDialog();
                break;
            case R.id.tv_tower_texture_v:
                selectTowerTextureDialog();
                break;
            case R.id.tv_tower_use_v:
                selectTowerUseDialog();
                break;
            case R.id.tv_tower_location_v:
                Toast.makeText(this, "location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_tower_setup_v:
                selectTowerSetupDialog();
                break;
            case R.id.tv_tower_terrain_v:
                selectTowerTerrainDialog();
                break;
            case R.id.tv_commissioning_date_v:
                selectCommissioningDateDialog();
                break;
        }
    }


    private void selectLineTypeDialog() {
        final String[] items = ConstantValues.ITEMS_LINE_TYPE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_line_type_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void selectTowerTextureDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_TEXTURE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_tower_texture_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void selectTowerUseDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_USE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_tower_use_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 同杆架设
    private void selectTowerSetupDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_SETUP;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_tower_setup_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 所处地形
    private void selectTowerTerrainDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_TERRAIN;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        tv_tower_terrain_v.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 投运时间
    private void selectCommissioningDateDialog() {
        new QMUIDialog.CustomDialogBuilder(this)
                .setLayout(R.layout.dialog_commissioning_date)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        DatePicker datePicker = dialog.findViewById(R.id.date_picker_commissioning);
                        if (datePicker != null) {
                            int year = datePicker.getYear();
                            int month = datePicker.getMonth() + 1;
                            int day = datePicker.getDayOfMonth();
                            StringBuilder sb = new StringBuilder();
                            sb.append(year).append("年").append(month).append("月").append(day).append("日");
                            tv_commissioning_date_v.setText(sb.toString());
                        }
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private Context getActivity() {
        return this;
    }
}
