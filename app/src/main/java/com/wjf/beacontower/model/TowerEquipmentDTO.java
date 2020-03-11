package com.wjf.beacontower.model;

import com.wjf.beacontower.db.TowerEquipmentDO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author xiaom
 * Time: 2020/1/19 17:23
 * Author: Lin.Li
 * Description:
 */
public class TowerEquipmentDTO implements Serializable {

    /**
     * 杆上设备名称
     */
    private String name;
    /**
     * 杆上设备型号
     */
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TowerEquipmentDTO() {
    }

    public TowerEquipmentDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TowerEquipmentDTO that = (TowerEquipmentDTO) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TowerEquipmentDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public TowerEquipmentDO convertToDO() {
        TowerEquipmentDO equipmentDO = new TowerEquipmentDO();
        equipmentDO.setName(getName());
        equipmentDO.setType(getType());
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String dateFormat = sdf.format(new Date());
        equipmentDO.setCreateTime(dateFormat);
        return equipmentDO;
    }

}
