package com.agri.irrigation.deviceservice.exceptions;

/**
 * This is a custom exception class created to use
 * across application related to device query
 */
public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
