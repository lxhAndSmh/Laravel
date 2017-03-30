package com.liu.laravel.ui.topic.detail;

import com.liu.laravel.bean.topic.Topic;
import com.liu.laravel.bean.topic.TopicDetail;
import com.liu.laravel.bean.user.User;
import com.orhanobut.logger.Logger;

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
 * 创建时间：2017/3/23 16:42
 * 修改人：liuxuhui
 * 修改时间：2017/3/23 16:42
 * 修改备注：
 */

public class TopicDetailPresenter implements TopicDetailContract.Presenter {

    private Subscription subscription;
    private static TopicDetailContract.DataManager detailManager;
    private static TopicDetailContract.View view;

    public TopicDetailPresenter(TopicDetailContract.DataManager detailManager, TopicDetailContract.View view) {
        this.detailManager = detailManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getTopicDetailData(double topicId) {
        subscription = detailManager.getTopicDetail(topicId)
                .map(new Func1<TopicDetail, Topic>() {
                    @Override
                    public Topic call(TopicDetail topicDetail) {
                        return topicDetail.getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Topic>() {
                               @Override
                               public void call(Topic topic) {
                                   Logger.json(topic.toString());
                                   User userInfo = topic.getUser().getData();
                                   view.setTitle(userInfo);
                                   String webUrl = topic.getLinks().getDetailsWebView();
                                   view.setWebUrl(webUrl);
                                   view.setCommentUrl(topic.getLinks().getRepliesWebView());
                                   view.setVoteNumber(String.valueOf(topic.getVoteCount()));
                                   view.setCommentNumber(String.valueOf(topic.getReplyCount()));
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
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
