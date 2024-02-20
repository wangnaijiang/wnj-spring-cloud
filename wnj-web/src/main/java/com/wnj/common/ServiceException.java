package com.wnj.common;

/**
 * @auther naijiang.wang
 * @date 17-11-28 下午8:30
 */
public class ServiceException extends RuntimeException{
    public ServiceException(){
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message,Throwable cause){
        super(message, cause);
    }
}
