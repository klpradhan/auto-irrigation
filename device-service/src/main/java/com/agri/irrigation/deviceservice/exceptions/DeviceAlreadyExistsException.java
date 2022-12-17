package com.agri.irrigation.deviceservice.exceptions;

public class DeviceAlreadyExistsException extends RuntimeException {

    public DeviceAlreadyExistsException() {
        super();
    }

    public DeviceAlreadyExistsException(String message) {
        super(message);
    }
}
