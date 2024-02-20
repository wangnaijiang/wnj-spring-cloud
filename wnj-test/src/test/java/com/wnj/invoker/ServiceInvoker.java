package com.wnj.invoker;

import java.util.concurrent.Callable;

public class ServiceInvoker<T> implements Callable<ServiceInvokeResult<T>> {
    private RpcCallBack<T> rpcCallBack;

    public ServiceInvoker(){}
    public ServiceInvoker(RpcCallBack<T> rpcCallBack) {
        this.rpcCallBack = rpcCallBack;
    }

    @Override
    public ServiceInvokeResult<T> call() throws Exception {
        try {
            return rpcCallBack.execute();
        } catch (Exception e) {
            return new ServiceInvokeResult(e);
        }
    }
}
