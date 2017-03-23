package com.liu.laravel.bean.topic;

import com.google.gson.annotations.SerializedName;
import com.liu.laravel.bean.BaseModel;
import com.liu.laravel.bean.user.UserInfo;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/15 16:27
 * 修改人：liuxuhui
 * 修改时间：2017/3/15 16:27
 * 修改备注：
 */

public class Topic extends BaseModel {

    private int id;
    private String title;
    @SerializedName("is_excellent")
    private boolean isExcellent;
    @SerializedName("reply_count")
    private int replyCount;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("vote_count")
    private int voteCount;

    private boolean favorite;

    private boolean attention;

    @SerializedName("vote_down")
    private boolean voteDown;

    @SerializedName("vote_up")
    private boolean voteUp;

    private LinksBean links;

    private UserInfo user;

    @SerializedName("last_reply_user")
    private LastReplyUser lastReplyUser;

    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExcellent() {
        return isExcellent;
    }

    public void setExcellent(boolean excellent) {
        isExcellent = excellent;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
    }

    public boolean isVoteDown() {
        return voteDown;
    }

    public void setVoteDown(boolean voteDown) {
        this.voteDown = voteDown;
    }

    public boolean isVoteUp() {
        return voteUp;
    }

    public void setVoteUp(boolean voteUp) {
        this.voteUp = voteUp;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public LastReplyUser getLastReplyUser() {
        return lastReplyUser;
    }

    public void setLastReplyUser(LastReplyUser lastReplyUser) {
        this.lastReplyUser = lastReplyUser;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class LastReplyUser extends BaseModel {

        private DataEntity data;

        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public static class DataEntity extends BaseModel {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }

    }

    public static class Category extends BaseModel {

        private CategoryList.Category data;

        public CategoryList.Category getData() {
            return data;
        }

        public void setData(CategoryList.Category data) {
            this.data = data;
        }

    }

    public static class LinksBean extends BaseModel{

        @SerializedName("details_web_view")
        private String detailsWebView;
        @SerializedName("replies_web_view")
        private String repliesWebView;
        @SerializedName("web_url")
        private String webUrl;

        public String getDetailsWebView() {
            return detailsWebView;
        }

        public void setDetailsWebView(String detailsWebView) {
            this.detailsWebView = detailsWebView;
        }

        public String getRepliesWebView() {
            return repliesWebView;
        }

        public void setRepliesWebView(String repliesWebView) {
            this.repliesWebView = repliesWebView;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }


}
