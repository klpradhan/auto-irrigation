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
            return new DeviceDTO(deviceObj.getId(), deviceObj.getSerialNum(), deviceObj.getStatus(), deviceObj.getAvailability(),
                    deviceObj.getAlertEmail(), deviceObj.getAlertPhone(), deviceObj.getCreatedDate(), deviceObj.getModifiedDate());
        } else {
            return null;
        }

    }

    public Device register(Device deviceObj) {
        try {
            deviceRepository.save(deviceObj);
        } catch (DataIntegrityViolationException de) {
            String msg = de.getCause().getMessage();
            Utils.log(msg, LogLevel.INFO, className);
        }


        return deviceRepository
                .findById(deviceObj.getId())
                .orElse(null);
    }

    public Device updateDetails(Device deviceObj) {
        deviceRepository.save(deviceObj);
        return deviceRepository
                .findById(deviceObj.getId())
                .orElse(null);
    }


    public void remove(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    public Device updateDeviceStatus(DeviceStatusDTO status) {
        Device obj = deviceRepository.findById(status.getDeviceID()).orElse(null);
        obj.setStatus(status.getStatus());
        deviceRepository.save(obj);
        return deviceRepository.findById(obj.getId()).orElse(null);
    }
}
