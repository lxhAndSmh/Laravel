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
                /**
                 * 变换：将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列
                 * map()和flatMap()都是将传入的参数转化之后返回另一种对象。
                 * 区别：map()是一对一的转换，flatMap()可以是一对多的转换，而且返回的是Observable对象
                 * 扩展：由于可以在嵌套的Observable中添加异步代码，flatMap()也常用语嵌套的异步操作，例如嵌套网络请求
                 */
                .flatMap(new Func1<Object, Observable<TopicList>>() {  /** FuncX和ActionX一样，也有多个，用于不同参数个数的方法，
                                                                           区别在于FunX包装的是有返回值的方法*/
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
                 * Scheduler线程的自由控制：利用subscribeOn()结合observeOn()来实现线程控制，让事件的产生的消费发生在不同的线程。
                 *
                 * 生产事件的执行线程（一般为本地读取，网络请求等耗时操作），在后台线程。
                 * io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
                 * 不要把计算工作放在 io() 中，可以避免创建不必要的线程。
                 *
                 * 不同于observeOn(),subscribeOn()的位置放在哪里都可以，但它只能调用一次。（注：当使用了多个 subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。）
                 */
                .subscribeOn(Schedulers.io())
                /**
                 * doOnSubscribe()发生在subseribe()刚开始，事件还未发送之前调用的，可以用来做一些准备工作(数据清零或展示加载进度)
                 * 默认情况下，doOnSubscribe()和 Subscriber.onStart()一样，执行在subscribe()发生的线程，但区别在于它可以指定线程；
                 * 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。（因为doOnSubscribe()发生在流程之前，
                 * 所以subscribeOn()是起作用的；区别于一个流程中subscribeOn()只能调用一次）
                 *
                 * 可以用作流程开始前的初始化
                 */
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.onRequestStart();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                /**
                 * observeOn()指定的是Subscriber发生的线程，而这个Subscriber并不是subscribe参数中的Subscriber,而是observeOn()执行时当前Observable所对应的Subscriber，
                 * ，即它的直接下级Subscriber。换句话说，observeOn()指定的是在它之后的操作所在的线程。因此，如果需要有多次切换线程的需求，只要在每个想要切换的线程位置调
                 * 用一次observeOn()即可（注：即observeOn()位置在需要切换线程的位置前面）
                 *
                 * 消费事件的执行线程，用于UI的更新，在主线程中。
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
                         *
                         * RxJava提供了多个ActionX形式的接口（例如Action0,Action1,Action2)，他们可以包装不同的无返回值的方法。
                         *                 X表示接口中的参数个数。
                         */
                        , new Action0() {
                            @Override
                            public void call() {
                                mView.onRequestEnd();
                            }
                        });
    }

    /**
     * 取消订阅
     */
    @Override
    public void unsubscribe() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
