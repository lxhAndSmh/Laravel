package com.liu.laravel.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名称：Laravel
 * 类描述：Tomast工具类
 * 创建人：liuxuhui
 * 创建时间：2017/3/14 17:24
 * 修改人：liuxuhui
 * 修改时间：2017/3/14 17:24
 * 修改备注：
 */

public class ToastUtils {

    public static void showShortTomast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
