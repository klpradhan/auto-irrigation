package com.agri.irrigation.plotservice.services;

import com.agri.irrigation.plotservice.dto.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceInfo {

    @Autowired
    RestTemplate restTemplate;

    public DeviceDTO getDeviceDetails(Long deviceId) {
        DeviceDTO device = restTemplate.getForObject("http://device-service/irrigation/api/v1/devices/"+deviceId, DeviceDTO.class);
        return device;
    }
}
