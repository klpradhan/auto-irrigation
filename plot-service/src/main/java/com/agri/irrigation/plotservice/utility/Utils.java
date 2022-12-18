package com.agri.irrigation.plotservice.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;

public class Utils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEVICE_NOT_FOUND_MSG = "Requested device not not registered yet";
    public static final String DEVICE_ALREADY_IN_USE = " device is already in use in some other plot";

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
