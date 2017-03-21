package com.liu.laravel.ui.topic.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liu.laravel.R;
import com.liu.laravel.bean.topic.Topic;
import com.liu.laravel.bean.topic.TopicList;
import com.liu.laravel.common.OnLoadMoreListener;
import com.liu.laravel.global.Constants;
import com.liu.laravel.ui.adapter.TopicAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
* 项目名称：TopicListFragment
* 类描述：Topic列表fragment
* 创建人：liuxuhui
* 创建时间：2017/3/17 18:00
* 修改人：liuxuhui
* 修改时间：2017/3/17 18:00
* 修改备注：
* @version
*/
public class TopicListFragment extends Fragment implements TopicListContact.View{

    private final String TAG = TopicListFragment.class.getSimpleName();

    private TopicListContact.Presenter presenter;
    private TopicListContact.DataManager manager;
    private Unbinder unbinder;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.loadAgain)
    ImageView imgLoad;

    private List<Topic> topics;
    private TopicAdapter adapter;
    public String TYPE = Constants.Topic.EXCELLENT;

    private int mPageIndex = 1;

    public TopicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initData(){
        manager = new TopicListDataManager();
        presenter = new TopicListPresenter(manager, this);
        topics = new ArrayList<>();
        adapter = new TopicAdapter(topics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                presenter.getTopicListByForm(TYPE, mPageIndex);
            }
        });
        presenter.getTopicListByForm(TYPE, mPageIndex);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPageIndex++;
                presenter.getTopicListByForm(TYPE, mPageIndex);
            }

        });
    }

    @Override
    public void refershTopicList(TopicList topicList) {
        topics.clear();
        if(topicList.getData() == null || topicList.getData().size() == 0){
            rlEmpty.setVisibility(View.VISIBLE);
            return;
        }else {
            rlEmpty.setVisibility(View.GONE);
        }
        topics.addAll(topicList.getData());
        adapter.replaceData(topics);
    }

    @Override
    public void loadMoreTopicList(TopicList topicList) {
        if(topicList.getData() != null && topicList.getData().size() != 0){
            topics.addAll(topicList.getData());
            adapter.replaceData(topics);
        }
    }

    @Override
    public void setPresenter(TopicListContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRequestStart() {
        if(mPageIndex == 1){
            swipeRefresh.setRefreshing(true);
        }
    }

    @Override
    public void onRequestError(String errorMsg) {
        Logger.d(TAG, "error=====" + errorMsg);
    }

    @Override
    public void onRequestEnd() {
        swipeRefresh.setRefreshing(false);
    }
}
