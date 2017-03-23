package com.liu.laravel.ui.topic.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liu.laravel.R;
import com.liu.laravel.bean.user.User;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicDetailActivity extends AppCompatActivity implements TopicDetailContract.View{

    private TopicDetailContract.DataManager mManager;
    private TopicDetailContract.Presenter mPresenter;

/*    @BindView(R.id.toolbar)
    Toolbar toolbar;*/
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.point_tv)
    TextView pointTv;
    @BindView(R.id.comment_tv)
    TextView commentTv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private double topicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        topicId = getIntent().getIntExtra("topicId", 0);
        mManager = new TopicDetailManager();
        mPresenter = new TopicDetailPresenter(mManager, this);
        mPresenter.getTopicDetailData(topicId);
        Logger.d("topicId====" + topicId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setWebUrl(String webUrl) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(webUrl);
    }

    @Override
    public void setTitle(User userInfo) {

    }

    @Override
    public void setCommentNumber(String commentNumber) {
        commentTv.setText(commentNumber);
    }

    @Override
    public void setVoteNumber(String voteNumber) {
        pointTv.setText(voteNumber);
    }

    @Override
    public void setPresenter(TopicDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onRequestStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestError(String errorMsg) {

    }

    @Override
    public void onRequestEnd() {
        progressBar.setVisibility(View.GONE);
    }
}
