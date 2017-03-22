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
    /**
     * Observable在subscribe()之后，会持有Subcriber的引用， 这个引用如果不能及时释放，将有内存泄漏的风险。
     * 一个原则：在不再使用的时候尽快在合适的地方（例如onPasuse(),onStop(),onDestory(）等方法中)调用unsubscribe()来解除引用关系，
     *          以避免内存泄漏的发生。调用之前可以先使用isUnsubscribed()判断一下状态
     */
    void unsubscribe();
}
