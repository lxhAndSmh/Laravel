package com.liu.laravel.ui.jsons;


import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.jsons.MapBean;

import rx.Observable;

/**
 * created by liuxuhui on 2018/5/2  下午9:45
 */
public class MapJsonDataManager implements MapJsonContract.DataManager {

    @Override
    public Observable<MapBean> getMapJson() {
        return ApiHttpClient.getInstance()
                .getMapJsonApi()
                .getMapJson();
    }
}
