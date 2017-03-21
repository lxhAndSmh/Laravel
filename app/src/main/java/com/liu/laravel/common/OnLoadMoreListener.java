package com.liu.laravel.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

/**
 * 项目名称：Laravel
 * 类描述：RecyclerView的LinearLayoutManager类型的加载更多
 * 创建人：liuxuhui
 * 创建时间：2017/3/21 15:59
 * 修改人：liuxuhui
 * 修改时间：2017/3/21 15:59
 * 修改备注：
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {

    private static final String TAG = OnLoadMoreListener.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;
    private int itemCount, lastPosition, lastItemCount;

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = linearLayoutManager.getItemCount();
            lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        }else {
            Logger.d(TAG, "The OnLoadMoreListener only support LinearLayoutManager");
            return;
        }
        if(lastItemCount != itemCount && lastPosition == itemCount - 1){
            lastItemCount = itemCount;
            this.onLoadMore();
        }
    }
}
