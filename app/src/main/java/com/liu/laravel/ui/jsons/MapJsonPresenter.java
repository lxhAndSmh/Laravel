package com.liu.laravel.ui.jsons;

import com.liu.laravel.bean.jsons.MapBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * created by liuxuhui on 2018/5/3  下午1:59
 */
public class MapJsonPresenter implements MapJsonContract.Presenter {

    private MapJsonContract.DataManager mDataManager;
    private MapJsonContract.View mView;
    private Subscription mSubscriptaion;

    public MapJsonPresenter(MapJsonContract.DataManager mDataManager, MapJsonContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getMapJsonData() {
        mSubscriptaion = mDataManager.getMapJson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MapBean>() {
                    @Override
                    public void call(MapBean mapBean) {
                        mView.setData(mapBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.setFailData(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mView.loadedData();
                    }
                });

    }

    @Override
    public void unsubscribe() {
        if(mSubscriptaion != null && !mSubscriptaion.isUnsubscribed()) {
            mSubscriptaion.unsubscribe();
        }
    }
}
