package com.liu.laravel.api;

import com.liu.laravel.bean.user.UserInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：用户API
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 16:07
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 16:07
 * 修改备注：
 */

public interface UserApi {

    @GET("me")
    Observable<UserInfo> getUserInfo();
}
