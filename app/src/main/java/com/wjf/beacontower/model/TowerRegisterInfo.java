package com.wjf.beacontower.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

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

    private String supplyName;
    private String transformerName;
    private String lineName;
    private String lineDuty;
    private String contactInfo;


    private String lineType;
    private String towerNum;
    private String subLineName;

    private String towerTexture;
    private String towerUse;
    private String towerLocation;
    private TowerLocationDTO locationDTO;
    private String towerHeight;
    private String towerSetup;
    private String wireType;
    private String towerEquipment;
    private String towerTerrain;
    private String commissioningDate;
    private String lineSpan;

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
        towerHeight = in.readString();
        towerSetup = in.readString();
        wireType = in.readString();
        towerEquipment = in.readString();
        towerTerrain = in.readString();
        commissioningDate = in.readString();
        lineSpan = in.readString();
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

    public String getWireType() {
        return wireType;
    }

    public void setWireType(String wireType) {
        this.wireType = wireType;
    }

    public String getTowerEquipment() {
        return towerEquipment;
    }

    public void setTowerEquipment(String towerEquipment) {
        this.towerEquipment = towerEquipment;
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

    public List<TowerEquipmentDTO> getTowerEquipmentDTOList() {
        return towerEquipmentDTOList;
    }

    public void setTowerEquipmentDTOList(List<TowerEquipmentDTO> towerEquipmentDTOList) {
        this.towerEquipmentDTOList = towerEquipmentDTOList;
    }

    public String getLineSpan() {
        return lineSpan;
    }

    public void setLineSpan(String lineSpan) {
        this.lineSpan = lineSpan;
    }

    public TowerLocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(TowerLocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(supplyName);
        parcel.writeString(transformerName);
        parcel.writeString(lineName);
        parcel.writeString(lineDuty);
        parcel.writeString(contactInfo);
        parcel.writeString(lineType);
        parcel.writeString(towerNum);
        parcel.writeString(subLineName);
        parcel.writeString(towerTexture);
        parcel.writeString(towerUse);
        parcel.writeString(towerLocation);
        parcel.writeString(towerHeight);
        parcel.writeString(towerSetup);
        parcel.writeString(wireType);
        parcel.writeString(towerEquipment);
        parcel.writeString(towerTerrain);
        parcel.writeString(commissioningDate);
        parcel.writeString(lineSpan);
    }


    public void clearData(){
        setLineType("");
        setTowerNum("");
        setSubLineName("");
        setTowerTexture("");
        setTowerUse("");
        setTowerLocation("");
        setTowerHeight("");
        setTowerSetup("");
        setWireType("");
        setTowerEquipment("");
        setTowerTerrain("");
        setCommissioningDate("");
        setLineSpan("");
        setTowerEquipmentDTOList(new ArrayList<>());
        setLocationDTO(null);
    }

    public void nextTower(String towerNum){
        setTowerNum(towerNum);
        setSubLineName("");
        setTowerTexture("");
        setTowerUse("");
        setTowerLocation("");
        setTowerHeight("");
        setTowerSetup("");
        setWireType("");
        setTowerEquipment("");
        setTowerTerrain("");
        setCommissioningDate("");
        setLineSpan("");
        setTowerEquipmentDTOList(new ArrayList<>());
        setLocationDTO(null);
    }

    public String objectToJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("commissioningDate", getCommissioningDate());
            jsonObject.putOpt("towerTerrain", getTowerTerrain());
            jsonObject.putOpt("wireType", getWireType());
            jsonObject.putOpt("towerSetup", getTowerSetup());
            jsonObject.putOpt("towerHeight", getTowerHeight());
//            jsonObject.putOpt("towerLocation", getTowerLocation());
            jsonObject.putOpt("towerUse", getTowerUse());
            jsonObject.putOpt("towerTexture", getTowerTexture());
            if (!TextUtils.isEmpty(getSubLineName())) {
                jsonObject.putOpt("subLineName", getSubLineName());
            }
            jsonObject.putOpt("towerNum", getTowerNum());
            jsonObject.putOpt("lineType", getLineType());
            jsonObject.putOpt("contactInfo", getContactInfo());
            jsonObject.putOpt("lineDuty", getLineDuty());
            jsonObject.putOpt("lineName", getLineName());
            jsonObject.putOpt("transformerName", getTransformerName());
            jsonObject.putOpt("supplyName", getSupplyName());
            jsonObject.putOpt("lineSpan", getLineSpan());

            if (towerEquipmentDTOList != null && towerEquipmentDTOList.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (TowerEquipmentDTO equipment:towerEquipmentDTOList) {
                    JSONObject jo=new JSONObject();
                    jo.put("name",equipment.getName());
                    jo.put("type",equipment.getType());
                    jsonArray.put(jo);
                }
                jsonObject.putOpt("EquipmentList", jsonArray);
            }
            if (getLocationDTO() != null) {
                JSONObject jo = new JSONObject();
                jo.putOpt("latitude",getLocationDTO().getLatitude());
                jo.putOpt("longitude",getLocationDTO().getLongitude());
                jo.putOpt("accuracy",getLocationDTO().getAccuracy());
                jo.putOpt("province",getLocationDTO().getProvince());
                jo.putOpt("city",getLocationDTO().getCity());
                jo.putOpt("district",getLocationDTO().getDistrict());
                jo.putOpt("type",getLocationDTO().getType());
                jsonObject.putOpt("locationDTO", jo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public String toString() {
        return "TowerRegisterInfo{" +
                "supplyName='" + supplyName + '\'' +
                ", transformerName='" + transformerName + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineDuty='" + lineDuty + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", lineType='" + lineType + '\'' +
                ", towerNum='" + towerNum + '\'' +
                ", subLineName='" + subLineName + '\'' +
                ", towerTexture='" + towerTexture + '\'' +
                ", towerUse='" + towerUse + '\'' +
                ", towerLocation='" + towerLocation + '\'' +
                ", towerHeight='" + towerHeight + '\'' +
                ", towerSetup='" + towerSetup + '\'' +
                ", wireType='" + wireType + '\'' +
                ", towerEquipment='" + towerEquipment + '\'' +
                ", towerTerrain='" + towerTerrain + '\'' +
                ", commissioningDate='" + commissioningDate + '\'' +
                ", lineSpan='" + lineSpan + '\'' +
                ", towerEquipmentDTOList=" + towerEquipmentDTOList +
                '}';
    }
}
