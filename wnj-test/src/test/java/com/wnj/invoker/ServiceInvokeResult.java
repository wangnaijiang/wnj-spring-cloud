package com.wnj.invoker;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceInvokeResult<T> {
    private boolean success;
    private T data;
    private Throwable throwable;

    public ServiceInvokeResult(){
        success = false;
        data = null;
        throwable = null;
    }
    public ServiceInvokeResult(T data) {
        this.success = true;
        this.data = data;
        this.throwable = null;
    }
    public ServiceInvokeResult(Throwable t) {
        this.success = false;
        this.data = null;
        this.throwable = t;
    }
}
