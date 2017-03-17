package com.liu.laravel.ui.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.laravel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：Laravel
 * 类描述：
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 18:24
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 18:24
 * 修改备注：
 */

public class TopicViewHoder extends RecyclerView.ViewHolder {

    @BindView(R.id.content_title_tv)
    TextView title;
    @BindView(R.id.content_time_tv)
    TextView time;
    @BindView(R.id.content_number_tv)
    TextView number;
    @BindView(R.id.user_img_iv)
    ImageView avater;

    public TopicViewHoder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
