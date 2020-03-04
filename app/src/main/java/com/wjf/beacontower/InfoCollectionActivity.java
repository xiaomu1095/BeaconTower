package com.wjf.beacontower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.transition.TransitionManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.wjf.beacontower.model.TowerEquipmentDTO;
import com.wjf.beacontower.model.TowerLocationDTO;
import com.wjf.beacontower.model.TowerRegisterInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InfoCollectionActivity extends BaseActivity implements AMapLocationListener {

    private Unbinder unbinder;
    private TowerRegisterInfo towerRegisterInfo;
    private final List<TowerEquipmentDTO> towerEquipmentDTOList = new ArrayList<>();
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @BindDimen(R.dimen.collect_gd_horizontal_15) int dimensionPixelSize;
    @BindDimen(R.dimen.collect_gd_horizontal_16) int dimensionPixelSize16;
    @BindDimen(R.dimen.collect_gd_horizontal_17) int dimensionPixelSize17;

    @BindArray(R.array.tower_gssb_spinner) String[] GSSBSpinnerArray;

    @BindView(R.id.fab) FloatingActionButton floatingActionButton;
    @BindView(R.id.constraintLayout) ConstraintLayout constraintLayout;
    @BindView(R.id.tv_supply_name_v) TextView tv_supply_name_v;
    @BindView(R.id.tv_line_name_v) TextView tv_line_name_v;
    @BindView(R.id.tv_line_duty_v) TextView tv_line_duty_v;
    @BindView(R.id.tv_contact_info_v) TextView tv_contact_info_v;
    @BindView(R.id.tv_line_type_v) TextView tv_line_type_v;
    @BindView(R.id.tv_tower_num_v) TextView tv_tower_num_v;
    @BindView(R.id.tv_subline_name_v) TextView tv_subline_name_v;
    @BindView(R.id.tv_tower_texture_v) TextView tv_tower_texture_v;
    @BindView(R.id.tv_tower_use_v) TextView tv_tower_use_v;
    @BindView(R.id.tv_tower_location_v) TextView tv_tower_location_v;
    @BindView(R.id.tv_tower_height_v) TextView tv_tower_height_v;
    @BindView(R.id.tv_tower_setup_v) TextView tv_tower_setup_v;
    @BindView(R.id.tv_wire_type_v) TextView tv_wire_type_v;
    @BindView(R.id.tv_tower_terrain_v) TextView tv_tower_terrain_v;
    @BindView(R.id.tv_commissioning_date_v) TextView tv_commissioning_date_v;
    @BindView(R.id.tv_line_span_v) TextView tv_line_span_v;
    @BindView(R.id.et_remark) TextView et_remark;
    @BindView(R.id.tv_wire_kind_v) TextView tv_wire_kind_v;
    @BindView(R.id.tv_wire_diameter_v) TextView tv_wire_diameter_v;
    @BindView(R.id.tv_investor_v) TextView tv_investor_v;
    @BindView(R.id.llc_equipment) LinearLayoutCompat llc_equipment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);
        unbinder = ButterKnife.bind(this);
        initToolbar();

        initData(savedInstanceState);

        initLocationClient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocationClient();
        if (unbinder != null) {
            unbinder.unbind();
        }
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
        }
        return super.onOptionsItemSelected(item);
    }

    // 清除用户填写的数据
    private void clearData() {
        tv_line_type_v.setText(null);
        tv_tower_num_v.setText(null);
        tv_subline_name_v.setText(null);
        tv_subline_name_v.setVisibility(View.GONE);

        removeCommonInfo();

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

        removeCommonInfo();

        if (towerRegisterInfo != null) {
            towerRegisterInfo.nextTower(nextTowerNum);
        }
    }

    private void removeCommonInfo() {
        tv_tower_texture_v.setText(null);
        tv_tower_use_v.setText(null);
        tv_tower_location_v.setText(null);
        tv_tower_height_v.setText(null);
        tv_tower_setup_v.setText(null);
        tv_wire_type_v.setText(null);
        tv_tower_terrain_v.setText(null);
        tv_commissioning_date_v.setText(null);
        tv_line_span_v.setText(null);
        et_remark.setText(null);
        tv_wire_kind_v.setText(null);
        tv_wire_diameter_v.setText(null);
        tv_investor_v.setText(null);

        towerEquipmentDTOList.clear();
        llc_equipment.removeAllViews();
        removeGSSB();
    }
    private void removeGSSB() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_155, dimensionPixelSize);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_16, dimensionPixelSize16);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_17, dimensionPixelSize17);
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSet.applyTo(constraintLayout);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setTitle("信息登记");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // 初始化FloatingActionButton
    @OnClick(R.id.fab)
    void onClickFloatingActionButton() {
        if (towerRegisterInfo == null) {
            return;
        }
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
        String lineSpan = tv_line_span_v.getText().toString();
        towerRegisterInfo.setLineSpan(lineSpan);
        towerRegisterInfo.setTowerEquipmentDTOList(towerEquipmentDTOList);
        String remark = et_remark.getText().toString();
        towerRegisterInfo.setRemark(remark);
        String wireKind = tv_wire_kind_v.getText().toString();
        towerRegisterInfo.setWireKind(wireKind);
        String wireDiameter = tv_wire_diameter_v.getText().toString();
        towerRegisterInfo.setWireDiameter(wireDiameter);
        String investor = tv_investor_v.getText().toString();
        towerRegisterInfo.setInvestor(investor);

        writeStringToFile(towerRegisterInfo.objectToJson());
        Snackbar.make(floatingActionButton, "数据已经存储", Snackbar.LENGTH_SHORT).show();
    }

    // 隐患登记
    @OnClick(R.id.et_remark)
    void addYHDJDialog() {
        final CharSequence et_remark_text = et_remark.getText();
        final QMUIDialog.CustomDialogBuilder builder = new QMUIDialog.CustomDialogBuilder(this);
        builder.setLayout(R.layout.dialog_add_yhdj)
                .setTitle("请输入信息")
                .setOnDecorationListener(new QMUIDialogView.OnDecorationListener() {
                    @Override
                    public void onDraw(Canvas canvas, QMUIDialogView view) {

                    }

                    @Override
                    public void onDrawOver(Canvas canvas, QMUIDialogView view) {
                        EditText editText = view.findViewById(R.id.dialog_et_remark);
                        if (editText != null) {
                            editText.setText(et_remark_text);
                            editText.setSelection(et_remark_text.length());
                        }
                    }
                })
                .addAction("确定", (dialog, index) -> {
                    AppCompatEditText editText = dialog.findViewById(R.id.dialog_et_remark);
                    if (editText != null) {
                        CharSequence text = editText.getText();
                        if (text != null && text.length() > 0) {
                            et_remark.setText(text);
                        }
                    }
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 线路跨越
    @OnClick(R.id.tv_line_span_v)
    void selectLineSpanDialog() {
        final String[] items = ConstantValues.ITEMS_LINE_SPAN;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    String item = items[which];
                    tv_line_span_v.setText(item);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 添加杆上设备
    @OnClick(R.id.tv_tower_equipment_v)
    void addGSSBDialog() {
        new QMUIDialog.CustomDialogBuilder(this)
                .setLayout(R.layout.dialog_add_gssb)
                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    Spinner spinner = dialog.findViewById(R.id.dia_spinner_sb);
                    EditText editText = dialog.findViewById(R.id.dia_et_xh);
                    if (spinner == null || editText == null) {
                        return;
                    }
                    int position = spinner.getSelectedItemPosition();
                    String string = GSSBSpinnerArray[position];
                    String xingHao = editText.getText().toString();
                    if (TextUtils.isEmpty(xingHao)) {
                        Toast.makeText(getActivity(), "请输入设备型号！", Toast.LENGTH_SHORT).show();
                    }
                    TowerEquipmentDTO equipment = new TowerEquipmentDTO(string, xingHao);
                    towerEquipmentDTOList.add(equipment);
                    addGSSBLayout(equipment);
                })
                .create(mCurrentDialogStyle).show();
    }
    private void addGSSBLayout(TowerEquipmentDTO equipment) {
        if (equipment == null) {
            return;
        }
        String text = equipment.getName() + " (型号：" + equipment.getType() + " )";
        int dpToPx = QMUIDisplayHelper.dpToPx(4);

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayoutCompat.LayoutParams layoutParams1 =
                new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(dpToPx, 10, 0, 10);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
        bfoOptions.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qmui_icon_notify_error, bfoOptions);
        LinearLayout.LayoutParams buttonLayoutPa = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        QMUIRadiusImageView2 imageView = new QMUIRadiusImageView2(getActivity());
        imageView.setImageBitmap(bitmap);
        buttonLayoutPa.setMarginEnd(QMUIDisplayHelper.dpToPx(6));
        imageView.setLayoutParams(buttonLayoutPa);
        imageView.setRadius(100);
        imageView.setCircle(true);
        imageView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bar_divider));
        imageView.setOnClickListener(v -> {
            TowerEquipmentDTO tag = (TowerEquipmentDTO) linearLayout.getTag();
            towerEquipmentDTOList.remove(tag);
            llc_equipment.removeView(linearLayout);
        });
        linearLayout.addView(imageView);

        LinearLayoutCompat.LayoutParams layoutParams =
                new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(dpToPx,0, 0, 0);
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(layoutParams);
        textView.setMaxLines(1);
        textView.setTextSize(14);
        textView.setText(text);
        linearLayout.addView(textView);
        linearLayout.setTag(equipment);

        llc_equipment.addView(linearLayout);

        llc_equipment.measure(0, 0);
        int h = llc_equipment.getMeasuredHeight();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_155, dimensionPixelSize + h);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_16, dimensionPixelSize16 + h);
        constraintSet.setGuidelineBegin(R.id.gd_horizontal_17, dimensionPixelSize17 + h);
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSet.applyTo(constraintLayout);
    }

    // 线路类型
    @OnClick(R.id.tv_line_type_v)
    void selectLineTypeDialog() {
        final String[] items = ConstantValues.ITEMS_LINE_TYPE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    String item = items[which];
                    tv_line_type_v.setText(item);
                    if ("支线".equals(item)) {
                        tv_subline_name_v.setVisibility(View.VISIBLE);
                    } else {
                        tv_subline_name_v.setVisibility(View.GONE);
                    }
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 输入杆号
    @OnClick(R.id.tv_tower_num_v)
    void inputTowerNumDialog() {
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
    @OnClick(R.id.tv_subline_name_v)
    void inputSubLineNameDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("请输入支线名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("确定", (dialog, index) -> {
                    CharSequence text = builder.getEditText().getText();
                    if (text != null && text.length() > 0) {
                        tv_subline_name_v.setText(text);
                    }
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 杆塔材质
    @OnClick(R.id.tv_tower_texture_v)
    void selectTowerTextureDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_TEXTURE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                    tv_tower_texture_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 杆塔用途
    @OnClick(R.id.tv_tower_use_v)
    void selectTowerUseDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_USE;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                    tv_tower_use_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 获取位置信息
    @OnClick(R.id.tv_tower_location_v)
    void getTowerLocation() {
        tv_tower_location_v.setText("开始定位");
        rxPermissions
                .request(locationPermissions)
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        // All requested permissions are granted
                        locationClient.setLocationListener(this);
                        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
                        locationClient.stopLocation();
                        locationClient.startLocation();
                    } else {
                        // At least one permission is denied
                        tv_tower_location_v.setText("权限不足");
                        new QMUIDialog.MessageDialogBuilder(getApplicationContext())
                                .setMessage("权限不足！请授权！")
                                .setTitle("授权提醒")
                                .addAction("确定", (dialog, index) -> dialog.dismiss())
                                .create().show();
                    }
                }, Throwable::printStackTrace);

    }

    // 杆塔高度
    @OnClick(R.id.tv_tower_height_v)
    void inputTowerHeight(){
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED |
                InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setTitle("请输入杆塔高度");
        builder.setPlaceholder("0");
        builder.addAction("确定", (dialog, index) -> {
            Editable text = builder.getEditText().getText();
            if (!TextUtils.isEmpty(text)) {
                tv_tower_height_v.setText(text);
            }
            dialog.dismiss();
        });
        builder.create(mCurrentDialogStyle).show();
    }

    // 同杆架设
    @OnClick(R.id.tv_tower_setup_v)
    void selectTowerSetupDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_SETUP;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                    tv_tower_setup_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 导线类型
    @OnClick(R.id.tv_wire_kind_v)
    void selectWireKindDialog() {
        final String[] items = ConstantValues.ITEMS_WIRE_KIND;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    tv_wire_kind_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 导线型号
    @OnClick(R.id.tv_wire_type_v)
    void inputWireType(){
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setTitle("请输入导线型号");
        builder.addAction("确定", (dialog, index) -> {
            Editable text = builder.getEditText().getText();
            if (!TextUtils.isEmpty(text)) {
                tv_wire_type_v.setText(text);
            }
            dialog.dismiss();
        });
        builder.create().show();
    }

    // 导线直径
    @OnClick(R.id.tv_wire_diameter_v)
    void inputWireDiameter(){
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setTitle("请输入导线直径");
        builder.addAction("确定", (dialog, index) -> {
            Editable text = builder.getEditText().getText();
            if (!TextUtils.isEmpty(text)) {
                tv_wire_diameter_v.setText(text);
            }
            dialog.dismiss();
        });
        builder.create().show();
    }

    // 所处地形
    @OnClick(R.id.tv_tower_terrain_v)
    void selectTowerTerrainDialog() {
        final String[] items = ConstantValues.ITEMS_TOWER_TERRAIN;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    Toast.makeText(getActivity(), "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                    tv_tower_terrain_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    // 投运时间
    @OnClick(R.id.tv_commissioning_date_v)
    void selectCommissioningDateDialog() {
        new QMUIDialog.CustomDialogBuilder(this)
                .setLayout(R.layout.dialog_commissioning_date)
                .addAction("确定", (dialog, index) -> {
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
                })
                .create().show();
    }

    // 投资方
    @OnClick(R.id.tv_investor_v)
    void selectInvestorDialog() {
        final String[] items = ConstantValues.ITEMS_INVESTOR;
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, (dialog, which) -> {
                    tv_investor_v.setText(items[which]);
                    dialog.dismiss();
                })
                .create(mCurrentDialogStyle).show();
    }

    private Context getActivity() {
        return this;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation == null) {
            Toast.makeText(this, "定位对象返回空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            TowerLocationDTO location = new TowerLocationDTO("AMap", aMapLocation.getLatitude(),
                    aMapLocation.getLongitude(),aMapLocation.getCity(),aMapLocation.getProvince(),
                    aMapLocation.getAccuracy(),aMapLocation.getDistrict());
            towerRegisterInfo.setLocationDTO(location);
            String locationString = aMapLocation.getLatitude() + "," + aMapLocation.getLongitude();
            towerRegisterInfo.setTowerLocation(locationString);
            tv_tower_location_v.setText(locationString);
        } else {
            //可以记录错误信息，或者根据错误错提示用户进行操作，Demo中只是打印日志
            Log.e("AMap", "定位失败，错误码：" + aMapLocation.getErrorCode() + ", " + aMapLocation.getLocationDetail());
            //提示错误信息
            tv_tower_location_v.setText("定位失败");
            Toast.makeText(this, "定位失败，错误码：" + aMapLocation.getErrorCode() + ", " + aMapLocation.getLocationDetail(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
