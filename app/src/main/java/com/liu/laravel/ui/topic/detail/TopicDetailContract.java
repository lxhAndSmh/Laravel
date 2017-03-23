package com.liu.laravel.ui.topic.detail;

import com.liu.laravel.bean.topic.TopicDetail;
import com.liu.laravel.bean.user.User;
import com.liu.laravel.common.BasePresenter;
import com.liu.laravel.common.BaseView;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：TopicDetail的合同类
 * 创建人：liuxuhui
 * 创建时间：2017/3/23 16:06
 * 修改人：liuxuhui
 * 修改时间：2017/3/23 16:06
 * 修改备注：
 */

public interface TopicDetailContract {

    interface DataManager{

        Observable<TopicDetail> getTopicDetail(double topicId);
    }

    interface View extends BaseView<Presenter> {

        void setWebUrl(String webUrl);

        void setTitle(User userInfo);

        void setCommentNumber(String commentNumber);

        void setVoteNumber(String voteNumber);
    }

    interface Presenter extends BasePresenter{

        void getTopicDetailData(double topicId);
    }
}
