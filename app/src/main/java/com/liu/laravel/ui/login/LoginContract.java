package com.liu.laravel.ui.login;

import android.content.Context;

import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.user.UserInfo;
import com.liu.laravel.common.BaseDataManager;
import com.liu.laravel.common.BasePresenter;
import com.liu.laravel.common.BaseView;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：登录页的合约类
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 15:55
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 15:55
 * 修改备注：
 */

public class LoginContract {

    interface DataManager extends BaseDataManager{

        Observable<Token> getLoginTokem(Context context, String userName, String loginTocken);

        Observable<UserInfo> login();
    }

    interface View extends BaseView<Presenter>{
        void onLoginSucess(UserInfo userInfo);
    }

    interface Presenter extends BasePresenter{
        void login(Context context, String userName, String loginTocken);
    }
}
