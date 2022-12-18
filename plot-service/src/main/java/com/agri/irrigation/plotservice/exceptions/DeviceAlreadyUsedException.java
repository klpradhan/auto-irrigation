package com.agri.irrigation.plotservice.exceptions;

public class DeviceAlreadyUsedException extends RuntimeException {
    public DeviceAlreadyUsedException() {
        super();
    }

    public DeviceAlreadyUsedException(String message) {
        super(message);
    }
}
