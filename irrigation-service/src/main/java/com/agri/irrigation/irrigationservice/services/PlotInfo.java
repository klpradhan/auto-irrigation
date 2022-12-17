package com.agri.irrigation.irrigationservice.services;

import com.agri.irrigation.irrigationservice.dto.PlotDTO;
import com.agri.irrigation.irrigationservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlotInfo {
    private final static String className = PlotInfo.class.getName();

    @Autowired
    RestTemplate restTemplate;

    public PlotDTO getPlotDetails(Long plotId) {
        PlotDTO plot = restTemplate.getForObject("http://localhost:8081/irrigation/api/v1/plots/"+plotId, PlotDTO.class);
        Utils.log(plot.toString(), LogLevel.INFO, className);
        System.out.println(plot.toString());
        return plot;
    }
}
