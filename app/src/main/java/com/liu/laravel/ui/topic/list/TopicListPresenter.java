package com.liu.laravel.ui.topic.list;

import android.text.TextUtils;

import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.topic.TopicList;
import com.orhanobut.logger.Logger;

import rx.Observable;
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
 * 创建时间：2017/3/17 16:52
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 16:52
 * 修改备注：
 */

public class TopicListPresenter implements TopicListContact.Presenter {

    private final TopicListContact.DataManager mDataManager;
    private final TopicListContact.View mView;
    private Subscription subscription;

    public TopicListPresenter(TopicListContact.DataManager mDataManager, TopicListContact.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getTopicListByForm(final String type, final int pageIndex) {
        /**
         * Observable在subscribe后，将传入的Subscriber作为Subscription返回，这是为了方便unsubscribe()解除订阅关系，以防内存泄漏
         */
        subscription = Observable.just(null)
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
                                                    Logger.d("Token===" + token.getAccess_token());
                                                }
                                            });
                                }
                                return Observable.just(throwable);
                            }
                        });
                    }
                })
                /**
                 * 生产事件的执行线程（一般为本地读取，网络请求等耗时操作），在后台线程。
                 * io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
                 * 不要把计算工作放在 io() 中，可以避免创建不必要的线程。
                 */
                .subscribeOn(Schedulers.io())
                /**
                 * doOnSubscribe()发生在subseribe()刚开始，事件还未发送之前调用的，可以用来做一些准备工作(数据清零或展示加载进度)
                 * 后面可以通过subcribeOn()指定线程执行。
                 */
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.onRequestStart();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                /**
                 * 指定 Subscriber 的回调发生在主线程
                 * 消费事件的执行线程，用于UI的更新，在主线程中
                 */
                .observeOn(AndroidSchedulers.mainThread())
                /**
                 * 不完整定义的回调，RxJava会自动根据定义创建出Subscriber。
                 * 注：Observer和Subscriber具有相同的角色（观察者，Subscriber是实现Observer的一个抽象类，区别在于多了onStart()和unsubscribe()两个方法
                 *    ，unsubscribe(): 这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅），
                 *    Observer在subscribe()过程中最终会被转换为Subscriber对象，因此使用Subscriber更合适点
                 */
                .subscribe(new Action1<TopicList>() {  /** 相当于Subscriber中的onNext()*/
                               @Override
                               public void call(TopicList topicList) {
                                   if(pageIndex == 1){
                                       mView.refershTopicList(topicList);
                                   }else {
                                       mView.loadMoreTopicList(topicList);
                                   }
                               }
                           }
                        /**
                         * 相当于Subscriber中的onError()
                         */
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.onRequestError(throwable.toString());
                            }
                        }
                        /**
                         * 相当于Subscriber中的onCompleted()
                         */
                        , new Action0() {
                            @Override
                            public void call() {
                                mView.onRequestEnd();
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
