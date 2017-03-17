package com.liu.laravel.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liu.laravel.R;
import com.liu.laravel.bean.topic.Topic;
import com.liu.laravel.ui.ViewHolder.TopicViewHoder;

import java.util.List;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 18:22
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 18:22
 * 修改备注：
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHoder> {

    private List<Topic> topics;

    public TopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }

    public void replaceData(List<Topic> topics){
        this.topics = topics;
        notifyDataSetChanged();
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_list, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        Topic topic = topics.get(position);
        String mCommentCount = String.valueOf(topic.getReplyCount());
        if(topic.getReplyCount() > 99){
            mCommentCount = "99+";
        }

        StringBuilder stringBuilder = new StringBuilder();
        if(topic.getCategory() != null){
            stringBuilder.append(topic.getCategory().getData().getName());
        }
        if(topic.getLastReplyUser() != null){
            stringBuilder.append(" * 最后由").append(topic.getLastReplyUser().getData().getName());
        }
        if(topic.getUpdatedAt() != null){
            stringBuilder.append(" * ").append(topic.getUpdatedAt());
        }


    }

    @Override
    public int getItemCount() {
        return topics.size();
    }
}
