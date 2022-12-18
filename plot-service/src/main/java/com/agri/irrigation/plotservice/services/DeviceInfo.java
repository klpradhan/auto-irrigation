package com.agri.irrigation.plotservice.services;

import com.agri.irrigation.plotservice.dto.DeviceDTO;
import com.agri.irrigation.plotservice.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceInfo {

    private final static String className = DeviceInfo.class.getName();

    @Autowired
    RestTemplate restTemplate;

    public DeviceDTO getDeviceDetails(Long deviceId) {
        DeviceDTO device = restTemplate.getForObject("http://device-service/irrigation/api/v1/devices/"+deviceId, DeviceDTO.class);
        Utils.log(device.toString(), LogLevel.INFO, className);
        return device;
    }
}
