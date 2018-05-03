package com.liu.laravel.ui.topic.comment;

import com.liu.laravel.bean.topic.TopicReply;

import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/29 16:31
 * 修改人：liuxuhui
 * 修改时间：2017/3/29 16:31
 * 修改备注：
 */

public class CommentPresenter implements CommentContract.Presenter {

    private CommentContract.DataManager dataManager;

    private CommentContract.View view;
    private Subscription subscription;

    public CommentPresenter(CommentContract.DataManager dataManager, CommentContract.View view) {
        this.dataManager = dataManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void publishComment(Map<String, String> options) {
        subscription = dataManager.publishComment(options)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.onRequestStart();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TopicReply>() {
                               @Override
                               public void call(TopicReply topicReply) {
                                   view.setCommentList(topicReply);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.onRequestError(throwable.toString());
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                view.onRequestEnd();
                            }
                        });
    }

    @Override
    public void unsubscribe() {

        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
