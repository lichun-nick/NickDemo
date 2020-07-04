package com.inspur.lib_base.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lichun
 * 线程池管理
 */
public class ThreadPoolManager {

    /**
     * 两个核心线程，最多三个线程
     */
    public final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new BlockChainThreadFactory());

    public static <T> Future<T> execute(Callable<T> callable){
        return THREAD_POOL_EXECUTOR.submit(callable);
    }

    public static void shutDown(){
        THREAD_POOL_EXECUTOR.shutdown();
    }


}
