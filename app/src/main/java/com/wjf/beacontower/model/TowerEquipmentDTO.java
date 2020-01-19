package com.wjf.beacontower.model;

import java.io.Serializable;

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
}
