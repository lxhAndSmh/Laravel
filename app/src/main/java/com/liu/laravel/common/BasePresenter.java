package com.liu.laravel.common;

/**
 * 项目名称：Laravel
 * 类描述：Presenter层，将Model层和View层关联起来
 * 创建人：liuxuhui
 * 创建时间：2017/3/17 12:05
 * 修改人：liuxuhui
 * 修改时间：2017/3/17 12:05
 * 修改备注：
 */

public interface BasePresenter {

    void subscribe();

    void unsubscribe();
}
