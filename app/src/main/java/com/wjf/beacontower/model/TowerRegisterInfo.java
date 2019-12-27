package com.wjf.beacontower.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

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
    private String towerHeight;
    private String towerSetup;
    private String wireType;
    private String towerEquipment;
    private String towerTerrain;
    private String commissioningDate;

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
    }



}
