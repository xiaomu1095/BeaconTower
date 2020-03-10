package com.wjf.beacontower.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author xiaom
 * Time: 2020/3/10 11:34
 * Author: Lin.Li
 * Description:
 */
@Entity(tableName = "tbl_location")
public class TowerLocationDO {

    @ColumnInfo(name = "id")// Room 列注解
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * 定位类型：高德(AMap)
     */
    @ColumnInfo(name = "type")// Room 列注解
    private String type;
    @ColumnInfo(name = "latitude")// Room 列注解
    private double latitude;
    @ColumnInfo(name = "longitude")// Room 列注解
    private double longitude;
    /**
     * 城市
     */
    @ColumnInfo(name = "city")// Room 列注解
    private String city;
    /**
     * 省份
     */
    @ColumnInfo(name = "province")// Room 列注解
    private String province;
    /**
     * 精度，单位：米
     */
    @ColumnInfo(name = "accuracy")// Room 列注解
    private float accuracy;
    /**
     * 区域
     */
    @ColumnInfo(name = "district")// Room 列注解
    private String district;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}