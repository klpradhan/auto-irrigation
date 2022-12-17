package com.agri.irrigation.irrigationservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;

public class Utils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String IRRIGATION_SUCCESS_MESG = "Irrigation is successfully done on plot :";

    public static void log(String msg, LogLevel logLevel, String loggerName) {
        Logger LOGGER = LoggerFactory.getLogger(loggerName);
        switch(logLevel) {
            case INFO:
                LOGGER.info(msg);
                break;
            case DEBUG:
                LOGGER.debug(msg);
                break;
            case ERROR:
                LOGGER.error(msg);
                break;
            case WARN:
                LOGGER.warn(msg);
                break;
            default:
                LOGGER.trace(msg);
        }
    }


}
