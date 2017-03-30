package com.liu.laravel.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.google.zxing.Result;
import com.liu.laravel.bean.user.UserInfo;
import com.liu.laravel.global.Constants;
import com.liu.laravel.global.UserConstant;
import com.liu.laravel.util.ToastUtils;
import com.orhanobut.logger.Logger;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * 项目名称：Laravel
 * 类描述：登录扫码页
 * 创建人：liuxuhui
 * 创建时间：2017/3/30 16:29
 * 修改人：liuxuhui
 * 修改时间：2017/3/30 16:29
 * 修改备注：
 */

public class LoginActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, LoginContract.View {

    private ZXingScannerView scannerView;

    private ProgressBar progressBar;

    private LoginContract.DataManager dataManager;
    private LoginContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        initView();
        initData();
    }

    private void initView(){
        progressBar = new ProgressBar(this);
    }

    private void initData(){
        dataManager = new LoginDataManager();
        presenter = new LoginPresenter(dataManager, this);
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.startCamera();
        scannerView.setResultHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
        presenter.unsubscribe();
    }

    @Override
    public void onLoginSucess(UserInfo userInfo) {
        UserConstant.newInstance(this).setUserData(userInfo.getData().toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.Key.USER_DATA, userInfo);
        Intent intent =getIntent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRequestStart() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestError(String errorMsg) {
        Logger.e(errorMsg);
        ToastUtils.showShortTomast(this, "登录失败");
    }

    @Override
    public void onRequestEnd() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleResult(Result result) {

        String userName = "";
        String loginTocken = "";
        String stringReslt = result.getText();
        if(!TextUtils.isEmpty(stringReslt) && stringReslt.contains(",")){
            String[] data = stringReslt.split(",", 2);
            if(data.length == 2){
                userName = data[0];
                loginTocken = data[1];
            }
        }
        Logger.d("handleResult=====" + stringReslt);
        Logger.d("userName=====" + userName + "======loginTocken====" + loginTocken);
        presenter.login(this, userName, loginTocken);
    }
}
