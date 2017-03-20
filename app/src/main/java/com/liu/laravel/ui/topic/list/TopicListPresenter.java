package com.liu.laravel.ui.topic.list;

import android.text.TextUtils;

import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.topic.TopicList;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    public void getTopicListByForm(final String type, final int pageIndex) {

        Observable.just(null)
                .flatMap(new Func1<Object, Observable<TopicList>>() {
                    @Override
                    public Observable<TopicList> call(Object o) {
                        return TextUtils.isEmpty(ApiHttpClient.getmToken())
                                ? Observable.<TopicList>error(new NullPointerException("Token is null"))
                                : mDataManager.getTopicListByForum(type, pageIndex);
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if(throwable instanceof NullPointerException || throwable instanceof IllegalArgumentException){
                                    return mDataManager.getTokenByForum()
                                            .doOnNext(new Action1<Token>() {
                                                @Override
                                                public void call(Token token) {
                                                    ApiHttpClient.setmToken(token.getAccess_token());
                                                    Logger.d("Token===", token.getAccess_token());
                                                }
                                            });
                                }
                                return Observable.just(throwable);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
