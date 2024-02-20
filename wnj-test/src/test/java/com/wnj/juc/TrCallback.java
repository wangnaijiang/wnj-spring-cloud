package com.wnj.juc;

public interface TrCallback<T> {
    void onSuccess(T t);
    void onError(String error);
}