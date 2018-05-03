package com.liu.laravel.ui.jsons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.liu.laravel.R;
import com.liu.laravel.bean.jsons.FeatureBean;
import com.liu.laravel.bean.jsons.MapBean;
import com.liu.laravel.util.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapJsonActivity extends AppCompatActivity implements MapJsonContract.View{

    @BindView(R.id.map_error_tv)
    TextView mErrorTv;
    @BindView(R.id.map_json_tv)
    TextView mJsonTv;
    private List<FeatureBean> mMapJson = new ArrayList<>();

    private MapJsonContract.Presenter mPresenter;
    private MapJsonContract.DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_json);
        ButterKnife.bind(this);
        mDataManager = new MapJsonDataManager();
        mPresenter = new MapJsonPresenter(mDataManager, this);
        mPresenter.getMapJsonData();
    }

    @Override
    public void setData(MapBean mapBean) {
        if(mapBean != null && mapBean.future != null) {
            mMapJson.addAll(mapBean.future);
        }

        if(mMapJson.size() > 0) {
            for(int i = 0; i < mMapJson.size(); i++) {
                Logger.d(mMapJson.get(i).toString(), this);
            }
            mJsonTv.setText(mMapJson.get(0).toString());
        }else {
            mJsonTv.setText("没有数据");
        }
    }

    @Override
    public void setFailData(String errorMessage) {
        mErrorTv.setText(errorMessage);
    }

    @Override
    public void loadedData() {
        ToastUtils.showShortTomast(this, "请求结束");
    }

    @Override
    public void setPresenter(MapJsonContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String errorMsg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
