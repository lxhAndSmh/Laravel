package com.liu.laravel.ui.login;

import android.content.Context;

import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.user.UserInfo;
import com.liu.laravel.global.Constants;
import com.liu.laravel.util.SharedPreferencesUtils;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 16:14
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 16:14
 * 修改备注：
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.DataManager dataManager;
    private LoginContract.View view;
    private Subscription subscription;

    public LoginPresenter(LoginContract.DataManager dataManager, LoginContract.View view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    @Override
    public void login(final Context context, String userName, String loginTocken) {
        dataManager.getLoginTokem(context, userName, loginTocken)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.onRequestStart();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Token, Observable<UserInfo>>() {
                    @Override
                    public Observable<UserInfo> call(Token token) {
                        ApiHttpClient.setmToken(token.getAccess_token());
                        SharedPreferencesUtils.newInstance(context).putString(Constants.Key.TOKEN, token.getAccess_token());
                        return dataManager.login();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfo>() {
                               @Override
                               public void call(UserInfo userInfo) {
                                   view.onLoginSucess(userInfo);
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.onRequestError(throwable.toString());
                            }
                        }
                        , new Action0() {
                            @Override
                            public void call() {
                                view.onRequestEnd();
                            }
                        });
    }

    @Override
    public void unsubscribe() {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
