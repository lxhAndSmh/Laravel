package com.liu.laravel.ui.jsons;

import com.liu.laravel.bean.jsons.MapBean;
import com.liu.laravel.common.BasePresenter;
import com.liu.laravel.common.BaseView;

import rx.Observable;

/**
 * created by liuxuhui on 2018/5/2  下午9:34
 * 用于Map数据类型的解析测试
 */
public interface MapJsonContract {

    interface DataManager {
        Observable<MapBean> getMapJson();
    }

    interface View extends BaseView<Presenter> {
        void setData(MapBean mapBean);
        void setFailData(String errorMessage);
        void loadedData();
    }

    interface Presenter extends BasePresenter {
        void getMapJsonData();
    }
}
