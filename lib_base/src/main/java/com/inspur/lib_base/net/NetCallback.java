package com.inspur.lib_base.net;

/**
 * @author lichun
 */
public interface NetCallback<T>{
    /**
     * 成功返回
     * @param t
     */
    void onSuccess(T t);
    /**
     * 失败返回
     * @param t
     */
    void onFailure(T t);

    /**
     * 错误返回
     * @param t
     */
    void onError(T t);
}
