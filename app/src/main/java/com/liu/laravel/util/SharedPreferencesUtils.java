package com.liu.laravel.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名称：Laravel
 * 类描述：SharePreference工具类
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 14:36
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 14:36
 * 修改备注：
 */

public class SharedPreferencesUtils {

    private String TAG = SharedPreferencesUtils.class.getSimpleName();

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private static SharedPreferencesUtils utils;

    public static SharedPreferencesUtils newInstance(Context context){
        if(utils == null){
            utils = new SharedPreferencesUtils(context);
        }
        return utils;
    }

    private SharedPreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value){
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public String getString(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public void removeKey(String key){
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void putInt(String key, int value){
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key){
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void putBoolean(String key, boolean value){
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key){
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
