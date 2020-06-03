package com.inspur.lib_base.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author lichun
 */
public class ToastUtil {

    public static final void show(Context context,String msg){
        Toast.makeText(context.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
