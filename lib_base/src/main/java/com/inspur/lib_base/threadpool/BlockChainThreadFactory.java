package com.inspur.lib_base.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lichun
 */
public class BlockChainThreadFactory implements ThreadFactory {

    private static final String DEFAULT_POOL_NAME_PREFIX = "block_chain_pool_";
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private String poolNamePrefix;
    
    public BlockChainThreadFactory(){
        this.poolNamePrefix = DEFAULT_POOL_NAME_PREFIX;
    }
    
    public BlockChainThreadFactory(String poolNamePrefix){
        this.poolNamePrefix = poolNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,this.poolNamePrefix+threadNumber.getAndIncrement());
    }
}
