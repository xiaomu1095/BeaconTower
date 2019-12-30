package com.wjf.beacontower.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaom
 * Time: 2019/12/25 11:44
 * Author: Lin.Li
 * Description:
 */
public class SpUtil {
    private static final String file_name = "sp_name";

    public static boolean putString(Context context, String k, String v){
        SharedPreferences preferences = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if (!TextUtils.isEmpty(k) && !TextUtils.isEmpty(v)) {
            edit.putString(k, v);
        }
        return edit.commit();
    }

    public static boolean putString(Context context, Map<String, String> map){
        SharedPreferences preferences = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if (map != null) {
            for(Map.Entry<String, String> entry : map.entrySet()){
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                edit.putString(mapKey, mapValue);
            }
            return edit.commit();
        }
        return false;
    }


    public static Map<String, String> getAllApValues(Context context){
        Map<String, String> map = new HashMap<>();
        SharedPreferences preferences = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        Map<String, ?> preferencesAll = preferences.getAll();
        if (preferencesAll != null) {
            for(Map.Entry<String, ?> entry : preferencesAll.entrySet()){
                String mapKey = entry.getKey();
                String mapValue = String.valueOf(entry.getValue());
                map.put(mapKey, mapValue);
            }
        }
        return map;
    }

}
