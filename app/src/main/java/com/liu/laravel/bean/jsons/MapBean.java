package com.liu.laravel.bean.jsons;

import com.liu.laravel.bean.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * created by liuxuhui on 2018/5/2  下午9:36
 */
public class MapBean extends BaseModel {

    public List<FeatureBean> future;

    public MapBean(List<FeatureBean> future) {
        this.future = future;
    }
}
