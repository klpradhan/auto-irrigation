package com.agri.irrigation.irrigationservice.services;

import com.agri.irrigation.irrigationservice.dto.DeviceDTO;
import com.agri.irrigation.irrigationservice.dto.PlotDTO;
import com.agri.irrigation.irrigationservice.models.IrrigationAudit;
import com.agri.irrigation.irrigationservice.repository.IrrigationAuditRepository;
import com.agri.irrigation.irrigationservice.utils.DeviceStatus;
import com.agri.irrigation.irrigationservice.utils.IrrigationStatus;
import com.agri.irrigation.irrigationservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class IrrigationInfo {
    private final static String className = IrrigationInfo.class.getName();

    @Autowired
    DeviceInfo deviceInfo;

    @Autowired
    PlotInfo plotInfo;

    @Autowired
    AlertInfo alertInfo;

    @Autowired
    IrrigationAuditRepository irrigationAuditRepository;

    /**
     *
     * @param plotId : Get plot details using plot ID
     * @param retry : retry to check if the device is "ON"
     * @param retryDelay : retry interval
     * @return : Audit information with status
     */
    public IrrigationAudit irrigate(Long plotId, Integer retry, Integer retryDelay) throws InterruptedException {
        //Get plot information
        PlotDTO plot = plotInfo.getPlotDetails(plotId);

        //check for device status
        DeviceDTO device = plot.getDevice();
        if(DeviceStatus.ON.toString().equals(device.getStatus())) {
            System.out.println(Utils.IRRIGATION_SUCCESS_MESG + plotId);
            Utils.log(Utils.IRRIGATION_SUCCESS_MESG + plotId, LogLevel.INFO, className);
            return createAudit(plot, device, plotId);
        } else { // Retry by checking device status
            Utils.log("Going to " + retry + " time retry checking device", LogLevel.INFO, className);
            for(int x = 1; x <= retry; ++x) {
                Utils.log("Retry # " + x, LogLevel.INFO, className);
                plot = plotInfo.getPlotDetails(plotId);
                if(DeviceStatus.ON.toString().equals(device.getStatus())) {
                    System.out.println(Utils.IRRIGATION_SUCCESS_MESG + plotId);
                    Utils.log(Utils.IRRIGATION_SUCCESS_MESG + plotId, LogLevel.INFO, className);
                    return createAudit(plot, device, plotId);
                } else {
                    Utils.log("Going to wait for " + retryDelay + " secs", LogLevel.INFO, className);
                    TimeUnit.SECONDS.sleep(retryDelay);
                }
            }
            //Create an alert
            String alertMesg = Utils.IRRIGATION_FAILURE_MESG + plotId;
            Utils.log(alertMesg, LogLevel.ERROR, className);
            Long alertId = alertInfo.createAnAlert(plotId, device.getDeviceId(), new Date());
            return createAuditWithAlertDetails(plot, device, plotId, alertId, alertMesg);
        }
    }

    private IrrigationAudit createAuditWithAlertDetails(PlotDTO plot, DeviceDTO device, Long plotId, Long alertId, String alertMesg) {
        Date startTime = new Date();
        Long slotsTime = plot.getSlotsTime(); //in secs
        Date endTime;

        Calendar ca = Calendar.getInstance();
        ca.setTime(startTime);
        ca.add(Calendar.SECOND, slotsTime.intValue());
        endTime = ca.getTime();

        IrrigationAudit audit = new IrrigationAudit(plotId, device.getDeviceId(), slotsTime,
                IrrigationStatus.SUCCESS.toString(), alertId , alertMesg, startTime, endTime);
        try {
            irrigationAuditRepository.save(audit);
        } catch (Exception ignored) {
            return null;
        }
        return irrigationAuditRepository.findById(audit.getId()).orElse(null);
    }


    private IrrigationAudit createAudit(PlotDTO plot, DeviceDTO device, Long plotId) {
        Date startTime = new Date();
        Long slotsTime = plot.getSlotsTime(); //in secs
        Date endTime;

        Calendar ca = Calendar.getInstance();
        ca.setTime(startTime);
        ca.add(Calendar.SECOND, slotsTime.intValue());
        endTime = ca.getTime();

        IrrigationAudit audit = new IrrigationAudit(plotId, device.getDeviceId(), slotsTime,
                IrrigationStatus.SUCCESS.toString(), null , null, startTime, endTime);
        try {
            irrigationAuditRepository.save(audit);
        } catch (Exception ignored) {
            return null;
        }
        return irrigationAuditRepository.findById(audit.getId()).orElse(null);
    }
}
