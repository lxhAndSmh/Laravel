/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liu.laravel.bean.user;

import com.google.gson.annotations.SerializedName;
import com.liu.laravel.bean.BaseModel;
import com.liu.laravel.bean.topic.TopicDetail;

/**
* 项目名称：Message
* 类描述：
* 创建人：liuxuhui
* 创建时间：2017/3/16 16:45
* 修改人：liuxuhui
* 修改时间：2017/3/16 16:45
* 修改备注：
* @version
*/
public class Message extends BaseModel{
    private int id;

    private String type;

    private String body;

    @SerializedName("topic_id")
    private int topicId;

    @SerializedName("reply_id")
    private int replyId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("from_user_id")
    private int fromUserId;

    @SerializedName("type_msg")
    private String typeMsg;

    private String message;

    @SerializedName("from_user")
    private UserInfo fromUserEntity;

    private TopicDetail topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getTypeMsg() {
        return typeMsg;
    }

    public void setTypeMsg(String typeMsg) {
        this.typeMsg = typeMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getFromUserEntity() {
        return fromUserEntity;
    }

    public void setFromUserEntity(UserInfo fromUserEntity) {
        this.fromUserEntity = fromUserEntity;
    }

    public TopicDetail getTopic() {
        return topic;
    }

    public void setTopic(TopicDetail topic) {
        this.topic = topic;
    }
}
