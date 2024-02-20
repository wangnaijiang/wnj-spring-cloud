package com.wnj.common;

import lombok.Data;
import com.wnj.common.constant.ResultCode;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> buildSuccess(T data){
        Result result = new Result();
        result.setCode(ResultCode.RESULT_CODE_SUCCESS);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> buildFail(T data, int errorCode, String errorMsg){
        Result result = new Result();
        result.setCode(errorCode);
        result.setMsg(errorMsg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> buildFail4NoLogin(T data){
        Result result = new Result();
        result.setCode(ResultCode.RESULT_CODE_ILLEGAL_TOKEN);
        result.setMsg("当前会话未登录");
        result.setData(data);
        return result;
    }
}
