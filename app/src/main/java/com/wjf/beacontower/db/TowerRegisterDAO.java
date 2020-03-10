package com.wjf.beacontower.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

/**
 * @author xiaom
 * Time: 2020/3/10 11:14
 * Author: Lin.Li
 * Description:
 */
@Dao
public interface TowerRegisterDAO {

    @Query("SELECT tt.* FROM tbl_tower tt where tt.line_name=:lineName and tt.sub_line_name=:subLineName and tt.tower_num=:towerNum")
    Single<TowerRegisterDO> findOneByLineNameAndSubLineNameAndTowerNum(String lineName, String subLineName, String towerNum);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertNewOneRegister(TowerRegisterDO register);

    @Query("select * from tbl_location tl where tl.tid=:tid")
    Single<List<TowerLocationDO>> findLocationByTid(int tid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertNewOneLocation(TowerLocationDO location);

    @Query("select * from tbl_equipment te where te.tid=:tid")
    Single<List<TowerEquipmentDO>> findEquipmentByTid(int tid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertNewEquipment(List<TowerEquipmentDO> equipmentS);

}
