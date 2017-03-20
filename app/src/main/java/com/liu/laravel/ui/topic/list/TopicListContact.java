package com.liu.laravel.ui.topic.list;

import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.topic.TopicList;
import com.liu.laravel.common.BaseDataManager;
import com.liu.laravel.common.BasePresenter;
import com.liu.laravel.common.BaseView;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：Topic列表合同接口
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 11:48
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 11:48
 * 修改备注：
 */

public interface TopicListContact {

    interface DataManager extends BaseDataManager {

        Observable<Token> getTokenByForum();

        Observable<TopicList> getTopicListByForum(String type, int pageIndex);
    }

    interface View extends BaseView<Presenter>{

        void refershTopicList(TopicList topicList);

        void loadMoreTopicList(TopicList topicList);

    }

    interface Presenter extends BasePresenter{

        void getTopicListByForm(String type, int pageIndex);
    }
}
