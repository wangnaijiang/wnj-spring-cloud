package com.wnj.util;

import org.slf4j.Logger;

import java.text.MessageFormat;

public class LoggerUtil {
    public static void info(Logger logger, String template, Object... params){
        if(logger.isInfoEnabled()){
            logger.info(MessageFormat.format(template, params));
        }
    }

    public static void error(Logger logger, String template, Object... params){
        if(logger.isErrorEnabled()){
            logger.error(MessageFormat.format(template, params));
        }
    }

    public static void error(Throwable t, Logger logger, String template, Object... params){
        if(logger.isErrorEnabled()){
            logger.error(MessageFormat.format(template, params), t);
        }
    }

}
