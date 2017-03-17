package com.liu.laravel.ui.topic.list;

import com.liu.laravel.bean.topic.TopicList;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 16:52
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 16:52
 * 修改备注：
 */

public class TopicListPresenter implements TopicListContact.Presenter {

    private final TopicListContact.DataManager mDataManager;
    private final TopicListContact.View mView;

    public TopicListPresenter(TopicListContact.DataManager mDataManager, TopicListContact.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getTopicListByForm(String type, final int pageIndex) {

        mDataManager.getTopicListByForum(type, pageIndex)
                .subscribe(new Action1<TopicList>() {
                               @Override
                               public void call(TopicList topicList) {
                                   if(pageIndex == 1){
                                       mView.refershTopicList(topicList);
                                   }else {
                                       mView.loadMoreTopicList(topicList);
                                   }
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.onRequestError(throwable.toString());
                            }
                        }
                        , new Action0() {
                            @Override
                            public void call() {
                                mView.onRequestEnd();
                            }
                        });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
