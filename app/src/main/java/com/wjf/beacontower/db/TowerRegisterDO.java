package com.wjf.beacontower.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author xiaom
 * Time: 2020/3/10 11:12
 * Author: Lin.Li
 * Description:
 */
@Entity(tableName = "tbl_tower", indices = {@Index(value = {"line_name", "sub_line_name", "tower_num"})})
public class TowerRegisterDO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tid")// Room 列注解
    private int id;

    // 供电所名称
    @ColumnInfo(name = "supply_name")
    private String supplyName;
    // 变电站名称
    @ColumnInfo(name = "transformer_name")
    private String transformerName;
    // 线路名称
    @ColumnInfo(name = "line_name")
    private String lineName;
    // 线路专责
    @ColumnInfo(name = "line_duty")
    private String lineDuty;
    // 联系方式
    @ColumnInfo(name = "contact_info")
    private String contactInfo;

    // 线路类型
    @ColumnInfo(name = "line_type")
    private String lineType;
    // 杆塔编号
    @ColumnInfo(name = "tower_num")
    private String towerNum;
    // 支线名称
    @ColumnInfo(name = "sub_line_name")
    private String subLineName;

    // 杆塔材质
    @ColumnInfo(name = "tower_texture")
    private String towerTexture;
    // 杆塔用途
    @ColumnInfo(name = "tower_use")
    private String towerUse;
    // 杆塔位置
    @ColumnInfo(name = "tower_location")
    private String towerLocation;
    // 杆塔高度
    @ColumnInfo(name = "tower_height")
    private String towerHeight;
    // 同杆假设
    @ColumnInfo(name = "tower_setup")
    private String towerSetup;
    // 同杆线路名称
    @ColumnInfo(name = "tower_setup_name")
    private String towerSetupName;
    // 导线型号
    @ColumnInfo(name = "wire_type")
    private String wireType;
    // 导线种类：裸线，绝缘线
    @ColumnInfo(name = "wire_kind")
    private String wireKind;
    // 导线直径
    @ColumnInfo(name = "wire_diameter")
    private String wireDiameter;
    // 投资方
    @ColumnInfo(name = "investor")
    private String investor;
    // 所处地形
    @ColumnInfo(name = "tower_terrain")
    private String towerTerrain;
    // 投运时间
    @ColumnInfo(name = "commissioning_date")
    private String commissioningDate;
    // 线路跨越
    @ColumnInfo(name = "line_span")
    private String lineSpan;
    // 跨越线路名称杆号
    @ColumnInfo(name = "line_span_name")
    private String lineSpanName;
    // 隐患登记
    @ColumnInfo(name = "remark")
    private String remark;
    // 创建时间
    @ColumnInfo(name = "create_time", defaultValue = "CURRENT_TIMESTAMP")
    private String createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getTransformerName() {
        return transformerName;
    }

    public void setTransformerName(String transformerName) {
        this.transformerName = transformerName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineDuty() {
        return lineDuty;
    }

    public void setLineDuty(String lineDuty) {
        this.lineDuty = lineDuty;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getTowerNum() {
        return towerNum;
    }

    public void setTowerNum(String towerNum) {
        this.towerNum = towerNum;
    }

    public String getSubLineName() {
        return subLineName;
    }

    public void setSubLineName(String subLineName) {
        this.subLineName = subLineName;
    }

    public String getTowerTexture() {
        return towerTexture;
    }

    public void setTowerTexture(String towerTexture) {
        this.towerTexture = towerTexture;
    }

    public String getTowerUse() {
        return towerUse;
    }

    public void setTowerUse(String towerUse) {
        this.towerUse = towerUse;
    }

    public String getTowerLocation() {
        return towerLocation;
    }

    public void setTowerLocation(String towerLocation) {
        this.towerLocation = towerLocation;
    }

    public String getTowerHeight() {
        return towerHeight;
    }

    public void setTowerHeight(String towerHeight) {
        this.towerHeight = towerHeight;
    }

    public String getTowerSetup() {
        return towerSetup;
    }

    public void setTowerSetup(String towerSetup) {
        this.towerSetup = towerSetup;
    }

    public String getTowerSetupName() {
        return towerSetupName;
    }

    public void setTowerSetupName(String towerSetupName) {
        this.towerSetupName = towerSetupName;
    }

    public String getWireType() {
        return wireType;
    }

    public void setWireType(String wireType) {
        this.wireType = wireType;
    }

    public String getWireKind() {
        return wireKind;
    }

    public void setWireKind(String wireKind) {
        this.wireKind = wireKind;
    }

    public String getWireDiameter() {
        return wireDiameter;
    }

    public void setWireDiameter(String wireDiameter) {
        this.wireDiameter = wireDiameter;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getTowerTerrain() {
        return towerTerrain;
    }

    public void setTowerTerrain(String towerTerrain) {
        this.towerTerrain = towerTerrain;
    }

    public String getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(String commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public String getLineSpan() {
        return lineSpan;
    }

    public void setLineSpan(String lineSpan) {
        this.lineSpan = lineSpan;
    }

    public String getLineSpanName() {
        return lineSpanName;
    }

    public void setLineSpanName(String lineSpanName) {
        this.lineSpanName = lineSpanName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
