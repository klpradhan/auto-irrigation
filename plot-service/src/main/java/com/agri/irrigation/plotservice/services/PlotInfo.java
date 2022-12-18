package com.agri.irrigation.plotservice.services;

import com.agri.irrigation.plotservice.dto.DeviceDTO;
import com.agri.irrigation.plotservice.dto.PlotDTO;
import com.agri.irrigation.plotservice.exceptions.DeviceAlreadyUsedException;
import com.agri.irrigation.plotservice.exceptions.DeviceNotFoundException;
import com.agri.irrigation.plotservice.models.Plot;
import com.agri.irrigation.plotservice.repository.PlotRepository;
import com.agri.irrigation.plotservice.utility.DeviceAvailability;
import com.agri.irrigation.plotservice.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class PlotInfo {
    private static final String className = PlotInfo.class.getName();

    @Autowired
    DeviceInfo deviceInfo;

    @Autowired
    PlotRepository plotRepository;

    public PlotDTO getDetails(Long plotId) {
        PlotDTO plotDTO = null;
        DeviceDTO deviceDTO;
        Plot plot = plotRepository.findById(plotId).orElse(null);
        //Call Device service to get device details
        if(plot != null) {
            //Get device details
            deviceDTO = deviceInfo.getDeviceDetails(plot.getDeviceId());
            plotDTO = new PlotDTO(plot.getSlotsTime(), plot.getCropType(), plot.getArea(),
                                deviceDTO, plot.getCreatedDate(), plot.getModifiedDate());
        }
        Utils.log(plotDTO.toString(), LogLevel.INFO, className);
        return plotDTO;
    }


    public Plot register(Plot plot) {
        Plot plotObj = null;
        try {
            //Check Device existence, if it's OPEN then ready to register with the plot
            DeviceDTO deviceDTO = null;
            deviceDTO = deviceInfo.getDeviceDetails(plot.getDeviceId());
            if(deviceDTO == null) {
                Utils.log(Utils.DEVICE_NOT_FOUND_MSG, LogLevel.ERROR, className);
                throw new DeviceNotFoundException(Utils.DEVICE_NOT_FOUND_MSG);
            }
            if(DeviceAvailability.OPEN.toString().equals(deviceDTO.getAvailability())) {
                plotRepository.save(plot);
                plotObj = plotRepository.findById(plot.getId()).orElse(null);
            } else {
                throw new DeviceAlreadyUsedException(deviceDTO.getDeviceId() + Utils.DEVICE_ALREADY_IN_USE);
            }

        } catch (DataAccessException da) {
            Utils.log(da.getMessage(), LogLevel.FATAL, className);

        } finally {
            return plotObj;
        }
    }

    public Plot updateDetails(Plot plot) {
        Plot plotObj = null;
        try {
            //Check Device existence, if it's OPEN then ready to register with the plot
            DeviceDTO deviceDTO = null;
            deviceDTO = deviceInfo.getDeviceDetails(plot.getDeviceId());
            if(deviceDTO == null) {
                Utils.log(Utils.DEVICE_NOT_FOUND_MSG, LogLevel.ERROR, className);
                throw new DeviceNotFoundException(Utils.DEVICE_NOT_FOUND_MSG);
            }
            if(DeviceAvailability.OPEN.toString().equals(deviceDTO.getAvailability())) {
                plotRepository.save(plot);
                plotObj = plotRepository.findById(plot.getId()).orElse(null);
            } else {
                throw new DeviceAlreadyUsedException(deviceDTO.getDeviceId() + Utils.DEVICE_ALREADY_IN_USE);
            }

        } catch (DataAccessException da) {
            Utils.log(da.getMessage(), LogLevel.FATAL, className);

        } finally {
            return plotObj;
        }
    }


    public void remove(Long plotId) {
        try {
            plotRepository.deleteById(plotId);
        } catch (DataAccessException da) {
            Utils.log(da.getMessage(), LogLevel.FATAL, className);
        }
    }
}
