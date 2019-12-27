package com.wjf.beacontower;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class InfoCollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private TextView tv_line_type_v,tv_tower_num_v,tv_subline_name_v, tv_tower_texture_v, tv_tower_use_v,
            tv_tower_location_v, tv_tower_setup_v, tv_tower_terrain_v, tv_commissioning_date_v;
    private EditText et_tower_height_v, et_wire_type_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);
        initToolbar();
        initFAB();

        tv_line_type_v = findViewById(R.id.tv_line_type_v);
        tv_line_type_v.setOnClickListener(this);
        tv_tower_num_v = findViewById(R.id.tv_tower_num_v);
        tv_tower_num_v.setOnClickListener(this);
        tv_subline_name_v = findViewById(R.id.tv_subline_name_v);
        tv_subline_name_v.setOnClickListener(this);
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
            case R.id.menu_clear:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // 初始化FloatingActionButton
    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_line_type_v:
                selectLineTypeDialog();
                break;
            case R.id.tv_tower_num_v:
                inputTowerNumDialog();
                break;
            case R.id.tv_subline_name_v:
                inputSubLineNameDialog();
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

    // 线路类型
    private void selectLineTypeDialog() {
        final String[] items = ConstantValues.ITEMS_LINE_TYPE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = items[which];
                        tv_line_type_v.setText(item);
                        if ("支线".equals(item)) {
                            tv_subline_name_v.setVisibility(View.VISIBLE);
                        } else {
                            tv_subline_name_v.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 输入杆号
    private void inputTowerNumDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("请输入杆号")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            tv_tower_num_v.setText(text);
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 输入支线名称
    private void inputSubLineNameDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("请输入支线名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            tv_subline_name_v.setText(text);
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    // 杆塔材质
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

    // 杆塔用途
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
