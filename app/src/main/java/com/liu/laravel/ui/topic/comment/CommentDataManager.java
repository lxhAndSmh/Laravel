package com.liu.laravel.ui.topic.comment;

import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.topic.TopicReply;

import java.util.Map;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/29 16:26
 * 修改人：liuxuhui
 * 修改时间：2017/3/29 16:26
 * 修改备注：
 */

public class CommentDataManager implements CommentContract.DataManager {
    @Override
    public Observable<TopicReply> publishComment(Map<String, String> options) {
        return ApiHttpClient.getInstance()
                .getTopicApi()
                .publishReply(options);
    }
}
