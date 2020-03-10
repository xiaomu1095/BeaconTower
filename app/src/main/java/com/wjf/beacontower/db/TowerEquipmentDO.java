package com.wjf.beacontower.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_equipment")
public class TowerEquipmentDO {

    @ColumnInfo(name = "eid")// Room 列注解
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tid")// Room 列注解
    private int tid;

    /**
     * 杆上设备名称
     */
    @ColumnInfo(name = "name")// Room 列注解
    private String name;
    /**
     * 杆上设备型号
     */
    @ColumnInfo(name = "type")// Room 列注解
    private String type;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
