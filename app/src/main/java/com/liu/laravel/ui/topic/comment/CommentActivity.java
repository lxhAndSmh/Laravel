package com.liu.laravel.ui.topic.comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.liu.laravel.R;
import com.liu.laravel.bean.topic.TopicReply;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.send_iv)
    ImageView sendIv;

    private int topicId;
    private String mCommentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

    }

    private void initData(){

    }


    @Override
    public void setCommentList(TopicReply topicReply) {
        if(topicReply.getData() != null){
            commentEdt.setText("");

        }
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {

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
