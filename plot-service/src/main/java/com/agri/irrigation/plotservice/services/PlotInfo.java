package com.agri.irrigation.plotservice.services;

import com.agri.irrigation.plotservice.dto.DeviceDTO;
import com.agri.irrigation.plotservice.dto.PlotDTO;
import com.agri.irrigation.plotservice.models.Plot;
import com.agri.irrigation.plotservice.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class PlotInfo {

    @Autowired
    RestTemplate restTemplate;

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
        return plotDTO;
    }


    public Plot register(Plot plot) {
        try {
            plotRepository.save(plot);
        } catch (Exception e) {

        }
        return plotRepository.findById(plot.getId()).orElse(null);
    }

    public Plot updateDetails(Plot plot) {
        try {
            plotRepository.save(plot);
        } catch (Exception e) {

        }
        return plotRepository.findById(plot.getId()).orElse(null);
    }


    public void remove(Long plotId) {
        plotRepository.deleteById(plotId);
    }
}
