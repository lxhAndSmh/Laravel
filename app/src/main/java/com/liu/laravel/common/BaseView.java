package com.liu.laravel.common;

/**
 * 项目名称：Laravel
 * 类描述：View层，用于显示数据
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 12:02
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 12:02
 * 修改备注：
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void onRequestStart();

    void onRequestError(String errorMsg);

    void onRequestEnd();
}
