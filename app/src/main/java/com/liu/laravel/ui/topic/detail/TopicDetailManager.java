package com.liu.laravel.ui.topic.detail;

import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.topic.TopicDetail;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/23 16:32
 * 修改人：liuxuhui
 * 修改时间：2017/3/23 16:32
 * 修改备注：
 */

public class TopicDetailManager implements TopicDetailContract.DataManager{

    @Override
    public Observable<TopicDetail> getTopicDetail(double topicId) {
        return ApiHttpClient.getInstance()
                .getTopicApi()
                .getTopicDetail(topicId, getOptions());
    }

    private Map<String, String> getOptions() {
        Map<String, String> options = new HashMap<>();
        options.put("include", "user,node");
        options.put("columns", "root(excerpt),user(signature)");
        return options;
    }
}
