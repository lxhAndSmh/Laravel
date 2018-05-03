package com.liu.laravel.api;

import com.liu.laravel.bean.jsons.MapBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * created by liuxuhui on 2018/5/2  下午6:41
 * 用于测试map结构的数据解析测试
 */
public interface MapJsonApi {

    @GET("cs7CUQM35b01d869478a8296ef5cd75602bafc460030458?uri=map/future")
    Observable<MapBean> getMapJson();
}
