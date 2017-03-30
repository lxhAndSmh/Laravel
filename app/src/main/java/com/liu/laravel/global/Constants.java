package com.liu.laravel.global;

/**
 * 项目名称：Laravel
 * 类描述：全局常量
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 15:53
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 15:53
 * 修改备注：
 */

public class Constants {

    //每页请求数量
    public static final String PAGE_NUMBER = "10";

    public interface Topic {
        String EXCELLENT = "excellent";//推荐
        String NEWEST = "newest";//最新
        String VOTE = "vote";//热门
        String NOBODY = "nobody";//零回复
        String WIKI = "wiki";//社区WIKI
        String JOBS = "jobs";//热门招聘
    }

    public interface Token {
        String AUTH_TYPE_GUEST = "client_credentials";
        String AUTH_TYPE_USER = "login_token";
        String AUTH_TYPE_REFRESH = "refresh_token";
    }

    public interface Key{
        String TOPIC_ID = "topic_id";
        String COMMENT_URL = "comment_url";
        String USER_DATA = "user_data";
        String IS_LOGIN = "is_login";
        String WEB_URL = "web_url";
        String TOKEN = "token";
    }
}
