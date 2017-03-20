package com.liu.laravel.ui.topic.list;

import com.liu.laravel.BuildConfig;
import com.liu.laravel.api.ApiHttpClient;
import com.liu.laravel.bean.Token;
import com.liu.laravel.bean.topic.TopicList;
import com.liu.laravel.global.Constants;
import com.liu.laravel.util.RxSchedulers;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 15:26
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 15:26
 * 修改备注：
 */

public class TopicListDataManager implements TopicListContact.DataManager {

    @Override
    public Observable<Token> getTokenByForum() {
        return ApiHttpClient.getInstance()
                .getTokenApi()
                .getToken(Constants.Token.AUTH_TYPE_GUEST,
                        BuildConfig.CLIENT_ID,
                        BuildConfig.CLIENT_SECRET)
                .compose(RxSchedulers.<Token>io_main());
    }

    @Override
    public Observable<TopicList> getTopicListByForum(String type, int pageIndex) {
        return ApiHttpClient.getInstance()
                .getTopicApi()
                .getTopics(getOptionByforum(type, pageIndex))
                .compose(RxSchedulers.<TopicList>io_main());
    }

    private Map<String, String> getOptionByforum(String type, int pageIndex) {
        Map<String, String> options = new HashMap<>();
        options.put("include", "category,user,node,last_reply_user");
        options.put("per_page", Constants.PAGE_NUMBER);
        options.put("filters", type);
        options.put("page", String.valueOf(pageIndex));
        options.put("columns", "user(signature)");
        return options;
    }
}
