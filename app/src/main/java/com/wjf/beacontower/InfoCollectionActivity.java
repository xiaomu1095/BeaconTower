package com.wjf.beacontower;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.wjf.beacontower.model.TowerRegisterInfo;

import java.util.Iterator;

public class InfoCollectionActivity extends BaseActivity implements View.OnClickListener {

    private TowerRegisterInfo towerRegisterInfo;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private TextView tv_supply_name_v, tv_line_name_v, tv_line_duty_v, tv_contact_info_v;
    private TextView tv_line_type_v, tv_tower_num_v, tv_subline_name_v, tv_tower_texture_v, tv_tower_use_v,
            tv_tower_location_v, tv_tower_height_v, tv_tower_setup_v, tv_wire_type_v, tv_tower_terrain_v,
            tv_commissioning_date_v;
    private TextView tv_tower_equipment_v;

    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);
        initToolbar();
        initFAB();

        initView();
        initData(savedInstanceState);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                tv_tower_location_v.setText("onLocationChanged");
                String loc = location.getLatitude() + "," + location.getLongitude();
                tv_tower_location_v.setText(loc);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                tv_tower_location_v.setText("onStatusChanged" + s);
            }

            @Override
            public void onProviderEnabled(String s) {
                tv_tower_location_v.setText("onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String s) {
                tv_tower_location_v.setText("onProviderDisabled");
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null && locationListener != null) {
            tv_tower_location_v.setText("Remove Updates...");
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTowerLocation();
    }

    private void initData(Bundle savedInstanceState) {
        if (getIntent() == null) {
            return;
        }
        towerRegisterInfo = getIntent().getParcelableExtra(ConstantValues.BUNDLE_KEY_BASE_INFO);
        if (towerRegisterInfo == null) {
            return;
        }
        tv_supply_name_v.setText(towerRegisterInfo.getSupplyName());
        tv_line_name_v.setText(towerRegisterInfo.getLineName());
        tv_line_duty_v.setText(towerRegisterInfo.getLineDuty());
        tv_contact_info_v.setText(towerRegisterInfo.getContactInfo());
    }

    private void initView() {
        tv_supply_name_v = findViewById(R.id.tv_supply_name_v);
        tv_line_name_v = findViewById(R.id.tv_line_name_v);
        tv_line_duty_v = findViewById(R.id.tv_line_duty_v);
        tv_contact_info_v = findViewById(R.id.tv_contact_info_v);


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
        tv_tower_height_v = findViewById(R.id.tv_tower_height_v);
        tv_tower_height_v.setOnClickListener(this);
        tv_tower_setup_v = findViewById(R.id.tv_tower_setup_v);
        tv_tower_setup_v.setOnClickListener(this);
        tv_wire_type_v = findViewById(R.id.tv_wire_type_v);
        tv_wire_type_v.setOnClickListener(this);
        tv_tower_terrain_v = findViewById(R.id.tv_tower_terrain_v);
        tv_tower_terrain_v.setOnClickListener(this);
        tv_commissioning_date_v = findViewById(R.id.tv_commissioning_date_v);
        tv_commissioning_date_v.setOnClickListener(this);

        tv_tower_equipment_v = findViewById(R.id.tv_tower_equipment_v);
        tv_tower_equipment_v.setOnClickListener(this);
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
                clearData();
                break;
            case R.id.menu_next:
                nextTower();
                break;
            case R.id.menu_history:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 清除用户填写的数据
    private void clearData() {
        tv_line_type_v.setText(null);
        tv_tower_num_v.setText(null);
        tv_subline_name_v.setText(null);
        tv_subline_name_v.setVisibility(View.GONE);

        tv_tower_texture_v.setText(null);
        tv_tower_use_v.setText(null);
        tv_tower_location_v.setText(null);
        tv_tower_height_v.setText(null);
        tv_tower_setup_v.setText(null);
        tv_wire_type_v.setText(null);
        tv_tower_terrain_v.setText(null);
        tv_commissioning_date_v.setText(null);

        if (towerRegisterInfo != null) {
            towerRegisterInfo.clearData();
        }
    }

    // 用户填写下一个杆塔数据
    private void nextTower() {
        String towerNum = tv_tower_num_v.getText().toString().trim();
        int i;
        try {
            i = Integer.parseInt(towerNum);
        } catch (Exception e) {
            QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                    .setTipWord("杆塔编号不是数字！")
                    .create(false);
            dialog.show();
            tv_tower_num_v.postDelayed(dialog::dismiss, TIPS_LENGTH_SHORT);
            return;
        }
        String nextTowerNum = String.valueOf(i + 1);
        tv_tower_num_v.setText(nextTowerNum);
        if (tv_subline_name_v.getVisibility() == View.VISIBLE) {
            tv_subline_name_v.setText(null);
        }

        tv_tower_texture_v.setText(null);
        tv_tower_use_v.setText(null);
        tv_tower_location_v.setText(null);
        tv_tower_height_v.setText(null);
        tv_tower_setup_v.setText(null);
        tv_wire_type_v.setText(null);
        tv_tower_terrain_v.setText(null);
        tv_commissioning_date_v.setText(null);

        if (towerRegisterInfo != null) {
            towerRegisterInfo.nextTower(nextTowerNum);
        }
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
                if (towerRegisterInfo != null) {
                    String lineType = tv_line_type_v.getText().toString();
                    towerRegisterInfo.setLineType(lineType);
                    String towerNum = tv_tower_num_v.getText().toString();
                    towerRegisterInfo.setTowerNum(towerNum);
                    String subLineName = tv_subline_name_v.getText().toString();
                    towerRegisterInfo.setSubLineName(subLineName);
                    String towerTexture = tv_tower_texture_v.getText().toString();
                    towerRegisterInfo.setTowerTexture(towerTexture);
                    String towerUse = tv_tower_use_v.getText().toString();
                    towerRegisterInfo.setTowerUse(towerUse);
                    String towerLocation = tv_tower_location_v.getText().toString();
                    towerRegisterInfo.setTowerLocation(towerLocation);
                    String towerHeight = tv_tower_height_v.getText().toString();
                    towerRegisterInfo.setTowerHeight(towerHeight);
                    String towerSetup = tv_tower_setup_v.getText().toString();
                    towerRegisterInfo.setTowerSetup(towerSetup);
                    String wireType = tv_wire_type_v.getText().toString();
                    towerRegisterInfo.setWireType(wireType);
                    String towerTerrain = tv_tower_terrain_v.getText().toString();
                    towerRegisterInfo.setTowerTerrain(towerTerrain);
                    String commissioningDate = tv_commissioning_date_v.getText().toString();
                    towerRegisterInfo.setCommissioningDate(commissioningDate);

                    writeStringToFile(towerRegisterInfo.toString());
                }


                Snackbar.make(view, "数据已经存储", Snackbar.LENGTH_SHORT).show();
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
                getTowerLocation();
                break;
            case R.id.tv_tower_height_v:
                inputTowerHeight();
                break;
            case R.id.tv_tower_setup_v:
                selectTowerSetupDialog();
                break;
            case R.id.tv_wire_type_v:
                inputWireType();
                break;
            case R.id.tv_tower_terrain_v:
                selectTowerTerrainDialog();
                break;
            case R.id.tv_commissioning_date_v:
                selectCommissioningDateDialog();
                break;
            case R.id.tv_tower_equipment_v:

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
        if (tv_line_type_v.getText().length() < 1) {
            QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                    .setTipWord("请先选择线路类型！")
                    .create(false);
            dialog.show();
            tv_tower_num_v.postDelayed(dialog::dismiss, TIPS_LENGTH_SHORT);
            return;
        }
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("请输入杆塔编号")
                .setInputType(InputType.TYPE_CLASS_NUMBER)
                .addAction("确定", (dialog, index) -> {
                    CharSequence text = builder.getEditText().getText();
                    if (text != null && text.length() > 0) {
                        tv_tower_num_v.setText(text);
                    }
                    dialog.dismiss();
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

    // 获取位置信息
    private void getTowerLocation() {
        tv_tower_location_v.setText("start location");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 23);
                return;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
                10000, locationListener);
        locationManager.addGpsStatusListener(event -> {
            switch (event) {
                case GpsStatus.GPS_EVENT_STARTED:
                    tv_tower_location_v.setText("GPS_EVENT_STARTED");
                    break;
                case GpsStatus.GPS_EVENT_STOPPED:
                    tv_tower_location_v.setText("GPS_EVENT_STOPPED");
                    break;
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    tv_tower_location_v.setText("GPS_EVENT_FIRST_FIX");
                    break;
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    tv_tower_location_v.setText("GPS_EVENT_SATELLITE_STATUS");
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    GpsStatus gpsStatus = locationManager.getGpsStatus(null);
                    Iterable<GpsSatellite> gpsSatellites = gpsStatus.getSatellites();
                    int count = 0;
                    Iterator iterator = gpsSatellites.iterator();
                    while (iterator.hasNext()) {
                        count++;
                        iterator.next();
                    }
                    tv_tower_location_v.setText(count + "");
                    break;
            }
        });
    }

    // 杆塔高度
    private void inputTowerHeight(){
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setTitle("请输入杆塔高度");
        builder.setPlaceholder("0");
        builder.addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                Editable text = builder.getEditText().getText();
                if (!TextUtils.isEmpty(text)) {
                    tv_tower_height_v.setText(text);
                }
                dialog.dismiss();
            }
        });
        builder.create(mCurrentDialogStyle).show();
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

    // 导线类型
    private void inputWireType(){
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setTitle("请输入导线类型");
        builder.addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                Editable text = builder.getEditText().getText();
                if (!TextUtils.isEmpty(text)) {
                    tv_wire_type_v.setText(text);
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
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
