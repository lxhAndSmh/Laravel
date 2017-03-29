package com.liu.laravel.ui.topic.comment;

import com.liu.laravel.bean.topic.TopicReply;
import com.liu.laravel.common.BaseDataManager;
import com.liu.laravel.common.BasePresenter;
import com.liu.laravel.common.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/29 16:02
 * 修改人：liuxuhui
 * 修改时间：2017/3/29 16:02
 * 修改备注：
 */

public interface CommentContract {

    interface DataManager extends BaseDataManager{
        Observable<TopicReply> publishComment(Map<String, String> options);
    }

    interface View extends BaseView<Presenter>{

        void setCommentList(TopicReply topicReply);
    }

    interface Presenter extends BasePresenter{

        void publishComment(Map<String, String> options);
    }
}
