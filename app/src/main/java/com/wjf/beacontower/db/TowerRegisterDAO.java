package com.wjf.beacontower.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM tbl_tower")
    Single<List<TowerRegisterDO>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertNewOne(TowerRegisterDO towerRegisterDO);

    @Update
    int updateById(TowerRegisterDO towerRegisterDO);

}
