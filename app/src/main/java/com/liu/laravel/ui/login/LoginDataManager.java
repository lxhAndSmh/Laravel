package com.liu.laravel.ui.login;

import android.content.Context;

import com.liu.laravel.BuildConfig;
import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.user.UserInfo;
import com.liu.laravel.global.Constants;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 16:03
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 16:03
 * 修改备注：
 */

public class LoginDataManager implements LoginContract.DataManager {
    @Override
    public Observable<Token> getLoginTokem(Context context, String userName, String loginTocken) {
        return ApiHttpClient.getInstance()
                .getTokenApi()
                .getToken(Constants.Token.AUTH_TYPE_USER
                , BuildConfig.CLIENT_ID
                ,BuildConfig.CLIENT_SECRET
                ,userName, loginTocken);
    }

    @Override
    public Observable<UserInfo> login() {
        return ApiHttpClient.getInstance()
                .getUserApi()
                .getUserInfo();
    }
}
