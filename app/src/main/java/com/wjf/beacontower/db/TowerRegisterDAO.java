package com.wjf.beacontower.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author xiaom
 * Time: 2020/3/10 11:14
 * Author: Lin.Li
 * Description:
 */
@Dao
public interface TowerRegisterDAO {

    @Query("SELECT tt.* FROM tbl_tower tt where tt.line_name=:lineName and tt.sub_line_name=:subLineName and tt.tower_num=:towerNum")
    TowerRegisterDO findOneByLineNameAndSubLineNameAndTowerNum(String lineName, String subLineName, String towerNum);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertNewOneRegister(TowerRegisterDO register);

    @Update()
    Integer updateRegister(TowerRegisterDO register);

    @Query("select tl.* from tbl_location tl where tl.tid=:tid")
    List<TowerLocationDO> findLocationByTid(int tid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertNewOneLocation(TowerLocationDO location);

    @Query("select te.* from tbl_equipment te where te.tid=:tid")
    List<TowerEquipmentDO> findEquipmentByTid(int tid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertNewEquipment(List<TowerEquipmentDO> equipmentS);

}
