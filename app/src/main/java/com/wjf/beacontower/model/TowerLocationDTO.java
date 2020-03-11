package com.wjf.beacontower.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wjf.beacontower.db.TowerLocationDO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author xiaom
 * Time: 2020/1/20 8:54
 * Author: Lin.Li
 * Description:
 */
public class TowerLocationDTO implements Parcelable {
    /**
     * 定位类型：高德(AMap)
     */
    private String type;
    private double latitude;
    private double longitude;
    /**
     * 城市
     */
    private String city;
    /**
     * 省份
     */
    private String province;
    /**
     * 精度，单位：米
     */
    private float accuracy;
    /**
     * 区域
     */
    private String district;

    protected TowerLocationDTO(Parcel in) {
        type = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        city = in.readString();
        province = in.readString();
        accuracy = in.readFloat();
        district = in.readString();
    }

    public static final Creator<TowerLocationDTO> CREATOR = new Creator<TowerLocationDTO>() {
        @Override
        public TowerLocationDTO createFromParcel(Parcel in) {
            return new TowerLocationDTO(in);
        }

        @Override
        public TowerLocationDTO[] newArray(int size) {
            return new TowerLocationDTO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(city);
        dest.writeString(province);
        dest.writeFloat(accuracy);
        dest.writeString(district);
    }

    public TowerLocationDTO(String type, double latitude, double longitude, String city, String province, float accuracy, String district) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.province = province;
        this.accuracy = accuracy;
        this.district = district;
    }

    public TowerLocationDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TowerLocationDTO that = (TowerLocationDTO) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (Float.compare(that.accuracy, accuracy) != 0) return false;
        if (!type.equals(that.type)) return false;
        if (!city.equals(that.city)) return false;
        if (!province.equals(that.province)) return false;
        return district.equals(that.district);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + city.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + (accuracy != +0.0f ? Float.floatToIntBits(accuracy) : 0);
        result = 31 * result + district.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TowerLocationDTO{" +
                "type='" + type + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", accuracy=" + accuracy +
                ", district='" + district + '\'' +
                '}';
    }

    public TowerLocationDO convertToDO(){
        TowerLocationDO locationDO = new TowerLocationDO();
        locationDO.setAccuracy(getAccuracy());
        locationDO.setCity(getCity());
        locationDO.setDistrict(getDistrict());
        locationDO.setLatitude(getLatitude());
        locationDO.setLongitude(getLongitude());
        locationDO.setProvince(getProvince());
        locationDO.setType(getType());
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String dateFormat = sdf.format(new Date());
        locationDO.setCreateTime(dateFormat);
        return locationDO;
    }
}
