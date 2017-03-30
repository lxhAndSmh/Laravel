package com.liu.laravel.global;

import android.content.Context;

import com.google.gson.Gson;
import com.liu.laravel.bean.user.User;
import com.liu.laravel.util.SharedPreferencesUtils;

/**
 * 项目名称：Laravel
 * 类描述：用户的常量类
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 15:15
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 15:15
 * 修改备注：
 */

public class UserConstant {

    private static UserConstant constant;

    private Gson gson;

    private Context context;

    public static UserConstant newInstance(Context context){
        if(constant == null){
            constant = new UserConstant(context);
        }

        return constant;
    }

    private UserConstant(Context context){
        gson = new Gson();
        this.context = context;
    }

    public void setUserData(String userInfo){
        SharedPreferencesUtils.newInstance(context).putString(Constants.Key.USER_DATA, userInfo);
        setIsLogin(true);
    }

    public User getUserData(){
        String userData = SharedPreferencesUtils.newInstance(context).getString(Constants.Key.USER_DATA);
        return gson.fromJson(userData, User.class);
    }

    public void setIsLogin(boolean isLogin){
        SharedPreferencesUtils.newInstance(context).putBoolean(Constants.Key.IS_LOGIN, isLogin);
    }

    public boolean isLogin(){
        return SharedPreferencesUtils.newInstance(context).getBoolean(Constants.Key.IS_LOGIN);
    }

    public void logout(){
        SharedPreferencesUtils.newInstance(context).removeKey(Constants.Key.USER_DATA);
        setIsLogin(false);
    }

}
