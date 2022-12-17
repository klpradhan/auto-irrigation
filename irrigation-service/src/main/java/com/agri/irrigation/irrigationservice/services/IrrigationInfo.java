package com.agri.irrigation.irrigationservice.services;

import com.agri.irrigation.irrigationservice.models.IrrigationAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IrrigationInfo {

    @Autowired
    DeviceInfo deviceInfo;

    @Autowired
    PlotInfo plotInfo;

    /**
     *
     * @param plotId : Get plot details using plot ID
     * @param retry : retry to check if the device is "ON"
     * @param retryDelay : retry interval
     * @return : Audit information with status
     */
    public IrrigationAudit irrigate(Long plotId, Integer retry, Integer retryDelay) {
        //Get plot information

        //Call Device dervice to get device details
        deviceInfo.getDeviceDetails()
    }
}
