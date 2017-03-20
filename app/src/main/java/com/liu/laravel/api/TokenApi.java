package com.liu.laravel.api;

import com.liu.laravel.bean.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/20 16:47
 * 修改人：liuxuhui
 * 修改时间：2017/3/20 16:47
 * 修改备注：
 */

public interface TokenApi {
    /**
     * client_credentials 认证
     * @param authType
     * @param clientId
     * @param clientSecret
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/access_token")
    Observable<Token> getToken(@Field("grant_type") String authType,
                               @Field("client_id") String clientId,
                               @Field("client_secret") String clientSecret);

    /**
     * login_token 认证
     * @param authType
     * @param clientId
     * @param clientSecret
     * @param username
     * @param loginToken
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/access_token")
    Observable<Token> getToken(@Field("grant_type") String authType,
                                     @Field("client_id") String clientId,
                                     @Field("client_secret") String clientSecret,
                                     @Field("username") String username,
                                     @Field("login_token") String loginToken);
}
