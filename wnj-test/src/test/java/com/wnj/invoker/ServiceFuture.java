package com.wnj.invoker;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Getter
@Setter
public class ServiceFuture<T> implements Future<T> {
    private Future<ServiceInvokeResult<T>> future;

    private long timeout = 5000;

    public ServiceFuture(){}
    public ServiceFuture(Future<ServiceInvokeResult<T>> result, long timeout){
        this.future = result;
        this.timeout = timeout;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        ServiceInvokeResult<T> result = future.get(timeout, unit);
        if(result.isSuccess()){
            return result.getData();
        }
        throw new ExecutionException(result.getThrowable());
    }
}
