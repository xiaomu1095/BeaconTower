package com.wjf.beacontower.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wjf.beacontower.ConstantValues;

import java.io.File;

/**
 * @author xiaom
 * Time: 2020/3/10 11:21
 * Author: Lin.Li
 * Description:
 */
@Database(entities = {TowerRegisterDO.class}, version = 1, exportSchema = false)
public abstract class TowerDatabase extends RoomDatabase {

    private static volatile TowerDatabase INSTANCE;

    public abstract TowerRegisterDAO towerRegisterDAO();

    public static TowerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TowerDatabase.class) {
                if (INSTANCE == null) {
                    String dbName = "tower.db";
                    File info = context.getExternalFilesDir(ConstantValues.COLLECTION_INFO_PATH_NAME);
                    if (info != null && info.exists()) {
                        dbName = info.getAbsolutePath() + File.separatorChar + dbName;
                    }
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TowerDatabase.class, dbName)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
