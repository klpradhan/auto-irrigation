package com.agri.irrigation.plotservice.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
