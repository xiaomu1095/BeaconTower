package com.wjf.beacontower.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiaom
 * Time: 2020/3/10 11:34
 * Author: Lin.Li
 * Description:
 */
@Entity(tableName = "tbl_location", indices = {@Index(value = "tid")})
public class TowerLocationDO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lid")// Room 列注解
    private int id;

    @ColumnInfo(name = "tid")// Room 列注解
    private int tid;

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
    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time", defaultValue = "CURRENT_TIMESTAMP")
    private String createTime;


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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Ignore
    @NotNull
    @Override
    public String toString() {
        return "TowerLocationDO{" +
                "id=" + id +
                ", tid=" + tid +
                ", type='" + type + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", accuracy=" + accuracy +
                ", district='" + district + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
