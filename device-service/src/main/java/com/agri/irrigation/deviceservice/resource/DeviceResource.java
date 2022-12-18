package com.agri.irrigation.deviceservice.resource;

import com.agri.irrigation.deviceservice.dto.DeviceDTO;
import com.agri.irrigation.deviceservice.dto.DeviceStatusDTO;
import com.agri.irrigation.deviceservice.models.Device;
import com.agri.irrigation.deviceservice.services.DeviceInfo;
import com.agri.irrigation.deviceservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Device Management service endpoints
 */
@RestController
@RequestMapping(path="/irrigation/api/v1")
public class DeviceResource {
    private static final String className = DeviceResource.class.getName();

    @Autowired
    DeviceInfo deviceInfo;

    /**
     *
     * @param deviceId
     * @return Device details
     */
    @GetMapping(path = "/devices/{deviceId}")
    public ResponseEntity<DeviceDTO> getDeviceInfo(@PathVariable("deviceId") String deviceId) {
        DeviceDTO foundDevice = deviceInfo.getDetails(Long.valueOf(deviceId));
        if (foundDevice == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundDevice);
        }
    }

    /**
     *
     * @param deviceObj
     * @return Newly created Device object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/devices")
    public ResponseEntity<Device> addDevice(@RequestBody Device deviceObj)
            throws URISyntaxException {
        Device createdDevice = deviceInfo.register(deviceObj);
        if(createdDevice == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdDevice.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdDevice);
        }
    }

    /**
     *
     * @param deviceObj
     * @param deviceId
     * @return Updated device object
     */
    @PutMapping("/devices/{deviceId}")
    public ResponseEntity<Device> updateDevice(@RequestBody Device deviceObj, @PathVariable Long deviceId) {
        Device updatedDevice = deviceInfo.updateDetails(deviceObj);
        if (updatedDevice == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedDevice);
        }
    }

    /**
     *
     * @param deviceId : Device ID
     * @param status : Status ON / OFF
     * @return
     */
    @PutMapping("/devices/{deviceId}/status/{status}")
    public ResponseEntity<Device> updateDevice(@PathVariable("deviceId") String deviceId,
                                               @PathVariable("status") String status) {

        Device updatedDevice = null;
        try {
            updatedDevice = deviceInfo.updateDeviceStatus(Long.valueOf(deviceId), status);
        } catch (NumberFormatException ne) {
            Utils.log(ne.getMessage(), LogLevel.INFO, className);
            return ResponseEntity.notFound().build();
        }

        if (updatedDevice == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedDevice);
        }
    }

    /**
     *
     * @param deviceId
     * @return status
     */
    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Object> deleteDevice(@PathVariable Long deviceId) {
        deviceInfo.remove(deviceId);
        return ResponseEntity.noContent().build();
    }
}
