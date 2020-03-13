package com.wjf.beacontower.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.wjf.beacontower.db.TowerRegisterDO;
import com.wjf.beacontower.util.TimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaom
 * Time: 2019/12/27 15:30
 * Author: Lin.Li
 * Description:
 */
public class TowerRegisterInfo implements Parcelable {
    // 供电所名称
    private String supplyName;
    // 变电站名称
    private String transformerName;
    // 线路名称
    private String lineName;
    // 线路专责
    private String lineDuty;
    // 联系方式
    private String contactInfo;

    // 线路类型
    private String lineType;
    // 杆塔编号
    private String towerNum;
    // 支线名称
    private String subLineName;

    // 杆塔材质
    private String towerTexture;
    // 杆塔用途
    private String towerUse;
    // 杆塔位置
    private String towerLocation;
    private TowerLocationDTO locationDTO;
    // 杆塔高度
    private String towerHeight;
    // 同杆假设
    private String towerSetup;
    // 同杆线路名称
    private String towerSetupName;
    // 导线型号
    private String wireType = "";
    // 导线种类：裸线，绝缘线
    private String wireKind;
    // 导线直径
    private String wireDiameter;
    // 投资方
    private String investor;
    // 所处地形
    private String towerTerrain;
    // 投运时间
    private String commissioningDate;
    // 线路跨越
    private String lineSpan;
    // 跨越线路名称杆号
    private String lineSpanName;
    // 隐患登记
    private String remark;
    // 杆上设备
    private List<TowerEquipmentDTO> towerEquipmentDTOList;

    public TowerRegisterInfo(){

    }


    protected TowerRegisterInfo(Parcel in) {
        supplyName = in.readString();
        transformerName = in.readString();
        lineName = in.readString();
        lineDuty = in.readString();
        contactInfo = in.readString();
        lineType = in.readString();
        towerNum = in.readString();
        subLineName = in.readString();
        towerTexture = in.readString();
        towerUse = in.readString();
        towerLocation = in.readString();
        locationDTO = in.readParcelable(TowerLocationDTO.class.getClassLoader());
        towerHeight = in.readString();
        towerSetup = in.readString();
        towerSetupName = in.readString();
        wireType = in.readString();
        wireKind = in.readString();
        wireDiameter = in.readString();
        investor = in.readString();
        towerTerrain = in.readString();
        commissioningDate = in.readString();
        lineSpan = in.readString();
        lineSpanName = in.readString();
        remark = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(supplyName);
        dest.writeString(transformerName);
        dest.writeString(lineName);
        dest.writeString(lineDuty);
        dest.writeString(contactInfo);
        dest.writeString(lineType);
        dest.writeString(towerNum);
        dest.writeString(subLineName);
        dest.writeString(towerTexture);
        dest.writeString(towerUse);
        dest.writeString(towerLocation);
        dest.writeParcelable(locationDTO, flags);
        dest.writeString(towerHeight);
        dest.writeString(towerSetup);
        dest.writeString(towerSetupName);
        dest.writeString(wireType);
        dest.writeString(wireKind);
        dest.writeString(wireDiameter);
        dest.writeString(investor);
        dest.writeString(towerTerrain);
        dest.writeString(commissioningDate);
        dest.writeString(lineSpan);
        dest.writeString(lineSpanName);
        dest.writeString(remark);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TowerRegisterInfo> CREATOR = new Creator<TowerRegisterInfo>() {
        @Override
        public TowerRegisterInfo createFromParcel(Parcel in) {
            return new TowerRegisterInfo(in);
        }

        @Override
        public TowerRegisterInfo[] newArray(int size) {
            return new TowerRegisterInfo[size];
        }
    };

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

    public TowerLocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(TowerLocationDTO locationDTO) {
        this.locationDTO = locationDTO;
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

    public List<TowerEquipmentDTO> getTowerEquipmentDTOList() {
        return towerEquipmentDTOList;
    }

    public void setTowerEquipmentDTOList(List<TowerEquipmentDTO> towerEquipmentDTOList) {
        this.towerEquipmentDTOList = towerEquipmentDTOList;
    }

    public void clearData(){
        setLineType("");
        nextTower("");
    }

    public void nextTower(String towerNum){
        setTowerNum(towerNum);
        setSubLineName("");
        setTowerTexture("");
        setTowerUse("");
        setTowerLocation("");
        setTowerHeight("");
        setTowerSetup("");
        setTowerSetupName("");
        setWireType("");
        setTowerTerrain("");
        setCommissioningDate("");
        setLineSpan("");
        setLineSpanName("");
        setTowerEquipmentDTOList(new ArrayList<>());
        setLocationDTO(null);
        setRemark("");
        setWireKind("");
        setWireDiameter("");
        setInvestor("");
    }

    public String objectToJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("commissioningDate", getCommissioningDate());
            jsonObject.putOpt("towerTerrain", getTowerTerrain());
            jsonObject.putOpt("wireType", getWireType());
            jsonObject.putOpt("towerSetup", getTowerSetup());
            jsonObject.putOpt("towerSetupName", getTowerSetupName());
            jsonObject.putOpt("towerHeight", getTowerHeight());
            jsonObject.putOpt("towerUse", getTowerUse());
            jsonObject.putOpt("towerTexture", getTowerTexture());
            if (!TextUtils.isEmpty(getSubLineName())) {
                jsonObject.putOpt("subLineName", getSubLineName());
            } else {
                jsonObject.putOpt("subLineName", "");
            }
            jsonObject.putOpt("towerNum", getTowerNum());
            jsonObject.putOpt("lineType", getLineType());
            jsonObject.putOpt("contactInfo", getContactInfo());
            jsonObject.putOpt("lineDuty", getLineDuty());
            jsonObject.putOpt("lineName", getLineName());
            jsonObject.putOpt("transformerName", getTransformerName());
            jsonObject.putOpt("supplyName", getSupplyName());
            jsonObject.putOpt("lineSpan", getLineSpan());
            jsonObject.putOpt("lineSpanName", getLineSpanName());
            jsonObject.putOpt("remark", getRemark());
            jsonObject.putOpt("wireKind", getWireKind());
            jsonObject.putOpt("wireDiameter", getWireDiameter());
            jsonObject.putOpt("investor", getInvestor());

            JSONArray jsonArray = new JSONArray();
            if (towerEquipmentDTOList != null && towerEquipmentDTOList.size() > 0) {
                for (TowerEquipmentDTO equipment:towerEquipmentDTOList) {
                    JSONObject jo=new JSONObject();
                    jo.put("name",equipment.getName());
                    jo.put("type",equipment.getType());
                    jsonArray.put(jo);
                }
                jsonObject.putOpt("EquipmentList", jsonArray);
            } else {
                jsonObject.putOpt("EquipmentList", jsonArray);
            }
            JSONObject jo = new JSONObject();
            if (getLocationDTO() != null) {
                jo.putOpt("latitude",getLocationDTO().getLatitude());
                jo.putOpt("longitude",getLocationDTO().getLongitude());
                jo.putOpt("accuracy",getLocationDTO().getAccuracy());
                jo.putOpt("province",getLocationDTO().getProvince());
                jo.putOpt("city",getLocationDTO().getCity());
                jo.putOpt("district",getLocationDTO().getDistrict());
                jo.putOpt("type",getLocationDTO().getType());
                jsonObject.putOpt("locationDTO", jo);
            } else {
                jsonObject.putOpt("locationDTO", jo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public TowerRegisterDO convertToDO(){
        TowerRegisterDO registerDO = new TowerRegisterDO();
        registerDO.setSupplyName(getSupplyName());
        registerDO.setCommissioningDate(getCommissioningDate());
        registerDO.setContactInfo(getContactInfo());
        registerDO.setInvestor(getInvestor());
        registerDO.setWireType(getWireType());
        registerDO.setWireKind(getWireKind());
        registerDO.setWireDiameter(getWireDiameter());
        registerDO.setTransformerName(getTransformerName());
        registerDO.setLineDuty(getLineDuty());
        registerDO.setLineName(getLineName());
        registerDO.setLineSpan(getLineSpan());
        registerDO.setLineSpanName(getLineSpanName());
        registerDO.setLineType(getLineType());
        registerDO.setRemark(getRemark());
        registerDO.setSubLineName(getSubLineName());
        registerDO.setTowerHeight(getTowerHeight());
        registerDO.setTowerLocation(getTowerLocation());
        registerDO.setTowerNum(getTowerNum());
        registerDO.setTowerSetup(getTowerSetup());
        registerDO.setTowerSetupName(getTowerSetupName());
        registerDO.setTowerTerrain(getTowerTerrain());
        registerDO.setTowerTexture(getTowerTexture());
        registerDO.setTowerUse(getTowerUse());
        String now = TimeUtil.nowTimeString();
        registerDO.setCreateTime(now);
        registerDO.setUpdateTime(now);
        return registerDO;
    }

}
