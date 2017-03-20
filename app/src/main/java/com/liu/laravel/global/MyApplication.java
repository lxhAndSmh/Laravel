package com.liu.laravel.global;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/20 13:01
 * 修改人：liuxuhui
 * 修改时间：2017/3/20 13:01
 * 修改备注：
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
