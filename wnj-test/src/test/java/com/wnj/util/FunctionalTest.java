package com.wnj.util;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalTest {
    static String template = "{0},{1},{2},{3},{4},{5},{6}";
    private static Logger logger = LoggerFactory.getLogger(FunctionalTest.class);

    @Getter
    @Setter
    public static class Result<T>{
        private boolean success;
        private String errCode;
        private String errMsg;
        private T value;
    }
    @Getter
    @Setter
    public static class LogWrapper{
        private boolean success;
        private String errCode;
        private String errMsg;

        public static LogWrapper build(Result result) {
            LogWrapper logWrapper = new LogWrapper();
            logWrapper.success = result.success;
            logWrapper.errCode = result.errCode;
            logWrapper.errMsg = result.errMsg;
            return logWrapper;
        }
    }
    public static <T> T execute(Supplier<T> method, Function<T, LogWrapper> logBuilder, String log){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try{
            T result = method.get();
            stopWatch.stop();
            LogWrapper logWrapper = logBuilder.apply(result);
            String successDesc = logWrapper.isSuccess() ? "Y" : "N";
            String errCode = StringUtil.isBlank(logWrapper.getErrCode()) ? "-" : logWrapper.getErrCode();
            String errMsg = StringUtil.isBlank(logWrapper.getErrMsg()) ? "-" : logWrapper.getErrMsg();
            log("ORDER",stopWatch, successDesc, errCode, errMsg, log);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void log(String bizType, StopWatch stopWatch, String successDesc, String errCode, String errMsg, String log){
        String now = DateUtil.formatTime(new Date());
//        String formatLog = MessageFormat.format(template, now, bizType, stopWatch.getTotalTimeMillis(), successDesc, errCode, errMsg, log);
        LoggerUtil.info(logger,template, now, bizType, stopWatch.getLastTaskTimeMillis(), successDesc, errCode, errMsg, log);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            String value= "order-" +i;
            String log = "log content-" + i;
            FunctionalTest.execute(()->{
                ThreadUtil.sleep(RandomUtil.random(1000));
                Result result = new Result();
                if(index % 3== 0){
                    result.setSuccess(false);
                    result.setErrCode("ORDER_NOT_EXIST");
                    result.setErrMsg("订单不存在");
                }else{
                    result.setSuccess(true);
                    result.setValue(value);
                }
                return result;
            },LogWrapper::build, log);
        }
    }
}
