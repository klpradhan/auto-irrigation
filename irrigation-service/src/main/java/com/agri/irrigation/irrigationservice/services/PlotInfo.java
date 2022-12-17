package com.agri.irrigation.irrigationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlotInfo {
    @Autowired
    RestTemplate restTemplate;


}
