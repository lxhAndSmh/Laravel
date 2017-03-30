package com.liu.laravel.ui.topic.comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.liu.laravel.R;
import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.topic.TopicReply;
import com.liu.laravel.global.Constants;
import com.liu.laravel.util.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
* 项目名称：CommentActivity
* 类描述：评论列表
* 创建人：liuxuhui
* 创建时间：2017/3/29 17:04
* 修改人：liuxuhui
* 修改时间：2017/3/29 17:04
* 修改备注：
* @version
*/
public class CommentActivity extends AppCompatActivity implements CommentContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.comment_edt)
    AppCompatEditText commentEdt;

    private int mTopicId;
    private String mCommentUrl;

    private CommentContract.DataManager dataManager;
    private CommentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initData();
    }

    public static Intent newIntent(Context context, String commentUrl, int topicId){
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(Constants.Key.COMMENT_URL, commentUrl);
        intent.putExtra(Constants.Key.TOPIC_ID, topicId);
        return intent;
    }

    private void initData(){

        toolbar.setTitle("评论列表");
        setSupportActionBar(toolbar);

        mCommentUrl = getIntent().getStringExtra(Constants.Key.COMMENT_URL);
        mTopicId = getIntent().getIntExtra(Constants.Key.TOPIC_ID, 1);
        dataManager = new CommentDataManager();
        presenter = new CommentPresenter(dataManager, this);
        loadWebUrl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @OnClick(R.id.send_iv)
    public void sendComment(ImageView sendIv){
        String body = commentEdt.getText().toString();
        if(TextUtils.isEmpty(body)){
            ToastUtils.showShortTomast(this, "评论内容不能为空");
            return;
        }
        presenter.publishComment(getOptions(body));
    }

    @Override
    public void setCommentList(TopicReply topicReply) {
        if(topicReply.getData() != null){
            commentEdt.setText("");
            loadWebUrl();
            ToastUtils.showShortTomast(this, "评论成功");
        }
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRequestStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestError(String errorMsg) {
        Logger.e(errorMsg);
        ToastUtils.showShortTomast(this, "评论失败");
    }

    @Override
    public void onRequestEnd() {
    }

    private Map<String, String> getAuth() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + ApiHttpClient.getmToken());
        return header;
    }

    private void loadWebUrl(){
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webview.loadUrl(mCommentUrl, getAuth());
    }

    private Map<String, String> getOptions(String body){
        Map<String, String> options = new HashMap<>();
        options.put("topic_id", String.valueOf(mTopicId));
        options.put("body", body);
        return options;
    }

}
