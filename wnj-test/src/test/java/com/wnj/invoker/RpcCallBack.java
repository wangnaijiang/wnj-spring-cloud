package com.wnj.invoker;

public interface RpcCallBack<T> {
    ServiceInvokeResult<T> execute();
}
