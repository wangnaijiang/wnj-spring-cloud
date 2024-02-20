package com.wnj.invoker;

import java.util.function.Supplier;

public abstract class AbstractRpcCallBack<T> implements RpcCallBack<T>{
    @Override
    public ServiceInvokeResult execute() {
        return doExecute();
    }

    public abstract ServiceInvokeResult<T> doExecute();

    public static <E> AbstractRpcCallBack<E> build(Supplier<E> supplier){
//        return () -> {return new ServiceInvokeResult(supplier.get()); };
        return new AbstractRpcCallBack<E>() {
            @Override
            public ServiceInvokeResult<E> doExecute() {
                return new ServiceInvokeResult(supplier.get());
            }
        };
    }

}
