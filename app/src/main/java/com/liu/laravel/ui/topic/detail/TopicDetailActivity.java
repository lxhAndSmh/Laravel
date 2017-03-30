package com.liu.laravel.ui.topic.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.laravel.R;
import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.user.User;
import com.liu.laravel.ui.topic.comment.CommentActivity;
import com.liu.laravel.util.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicDetailActivity extends AppCompatActivity implements TopicDetailContract.View{

    private TopicDetailContract.DataManager mManager;
    private TopicDetailContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_info_rl)
    RelativeLayout rlUserInfo;
    @BindView(R.id.user_img_iv)
    SimpleDraweeView dvAvatar;
    @BindView(R.id.user_name_tv)
    TextView tvName;
    @BindView(R.id.user_desc_tv)
    TextView tvDesc;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.point_tv)
    TextView pointTv;
    @BindView(R.id.comment_tv)
    TextView commentTv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private int topicId;
    private String commentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        rlUserInfo.setVisibility(View.VISIBLE);
    }

    private void initData() {
        topicId = getIntent().getIntExtra("topicId", 0);
        mManager = new TopicDetailManager();
        mPresenter = new TopicDetailPresenter(mManager, this);
        mPresenter.getTopicDetailData(topicId);
        Logger.d("topicId====" + topicId);
    }

    @OnClick({R.id.point_tv, R.id.comment_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.point_tv:
                break;
            case R.id.comment_tv:
                startActivity(CommentActivity.newIntent(TopicDetailActivity.this, commentUrl, topicId));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toobar_menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                ToastUtils.showShortTomast(this, "分享");
                break;
            default:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setWebUrl(String webUrl) {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(webUrl, getAuth());
    }

    @Override
    public void setTitle(User userInfo) {
        dvAvatar.setImageURI(userInfo.getAvatar());
        tvName.setText(userInfo.getName());
        if (!TextUtils.isEmpty(userInfo.getIntroduction())) {
            tvDesc.setVisibility(View.VISIBLE);
            tvDesc.setText(userInfo.getIntroduction());
        }
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
    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
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
        Logger.e(errorMsg);
    }

    @Override
    public void onRequestEnd() {
        progressBar.setVisibility(View.GONE);
    }

    public Map<String, String> getAuth() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + ApiHttpClient.getmToken());
        return header;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
