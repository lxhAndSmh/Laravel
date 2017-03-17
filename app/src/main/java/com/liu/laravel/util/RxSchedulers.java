package com.liu.laravel.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：Laravel
 * 类描述：调度器工具类
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 16:28
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 16:28
 * 修改备注：
 */

public class RxSchedulers {

    public static <T>Observable.Transformer<T, T> io_main(){

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        //订阅事件所在线程
                        .subscribeOn(Schedulers.io())
                        //消费事件所在线程
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
