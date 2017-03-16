/*
 * Copyright 2016 Freelander
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liu.laravel.bean.topic;

import com.liu.laravel.bean.BaseModel;

/**
* 项目名称：TopicPublish
* 类描述：
* 创建人：liuxuhui
* 创建时间：2017/3/16 16:56
* 修改人：liuxuhui
* 修改时间：2017/3/16 16:56
* 修改备注：
* @version
*/
public class TopicPublish extends BaseModel{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String category_id;
        private String title;
        private String body;
        private String reply_count;
        private String vote_count;
        private boolean vote_up;
        private boolean vote_down;
        private String updated_at;

        private LinksBean links;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getVote_count() {
            return vote_count;
        }

        public void setVote_count(String vote_count) {
            this.vote_count = vote_count;
        }

        public boolean isVote_up() {
            return vote_up;
        }

        public void setVote_up(boolean vote_up) {
            this.vote_up = vote_up;
        }

        public boolean isVote_down() {
            return vote_down;
        }

        public void setVote_down(boolean vote_down) {
            this.vote_down = vote_down;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public static class LinksBean {
            private String details_web_view;
            private String replies_web_view;
            private String web_url;

            public String getDetails_web_view() {
                return details_web_view;
            }

            public void setDetails_web_view(String details_web_view) {
                this.details_web_view = details_web_view;
            }

            public String getReplies_web_view() {
                return replies_web_view;
            }

            public void setReplies_web_view(String replies_web_view) {
                this.replies_web_view = replies_web_view;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }
        }
    }
}
