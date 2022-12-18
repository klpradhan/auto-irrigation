package com.agri.irrigation.deviceservice.services;

import com.agri.irrigation.deviceservice.dto.DeviceDTO;
import com.agri.irrigation.deviceservice.dto.DeviceStatusDTO;
import com.agri.irrigation.deviceservice.models.Device;
import com.agri.irrigation.deviceservice.repository.DeviceRepository;
import com.agri.irrigation.deviceservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceInfo {
    private static final String className = DeviceInfo.class.getName();

    @Autowired
    DeviceRepository deviceRepository;

    public DeviceDTO getDetails(Long deviceId) {
        Device deviceObj = deviceRepository
                .findById(deviceId)
                .orElse(null);
        if (deviceObj != null) {
            Utils.log("Found device : " + deviceObj.getSerialNum(), LogLevel.INFO, className);
            DeviceDTO deviceDTO = new DeviceDTO(deviceObj.getId(), deviceObj.getSerialNum(), deviceObj.getStatus(), deviceObj.getAvailability(),
                    deviceObj.getAlertEmail(), deviceObj.getAlertPhone(), deviceObj.getCreatedDate(), deviceObj.getModifiedDate());
            Utils.log(deviceDTO.toString(), LogLevel.INFO, className);
            return deviceDTO;
        } else {
            Utils.log("Failed to find device : " + deviceId, LogLevel.INFO, className);
            return null;
        }
    }

    public Device register(Device deviceObj) {
        Device device = null;
        try {
            deviceRepository.save(deviceObj);
            device = deviceRepository
                    .findById(deviceObj.getId())
                    .orElse(null);
        } catch (DataIntegrityViolationException de) {
            String msg = de.getCause().getMessage();
            Utils.log(msg, LogLevel.ERROR, className);
        } finally {
            return device;
        }
    }

    public Device updateDetails(Device deviceObj) {
        Device device = null;
        try {
            deviceRepository.save(deviceObj);
            device = deviceRepository
                    .findById(deviceObj.getId())
                    .orElse(null);
        } catch (DataIntegrityViolationException de) {
            String msg = de.getCause().getMessage();
            Utils.log(msg, LogLevel.ERROR, className);
        } finally {
            return device;
        }
    }


    public void remove(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    public Device updateDeviceStatus(Long deviceId, String status) {
        Device obj = deviceRepository.findById(deviceId).orElse(null);
        if(obj == null) {
            Utils.log(Utils.DEVICE_NOT_FOUND_MSG, LogLevel.INFO, className);
            return null;
        } else {
            Utils.log("Found Device : " + obj.getSerialNum(), LogLevel.INFO, className);
            Utils.log("Updating device status to : " + status, LogLevel.INFO, className);
            obj.setStatus(status);
            deviceRepository.save(obj);
            return deviceRepository.findById(obj.getId()).orElse(null);
        }
    }
}
