package com.agri.irrigation.irrigationservice.services;

import com.agri.irrigation.irrigationservice.dto.AlertDTO;
import com.agri.irrigation.irrigationservice.dto.AlertResponseDTO;
import com.agri.irrigation.irrigationservice.utils.Utils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.SimpleDoc;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AlertInfo {
    private static final String className = AlertInfo.class.getName();

    @Autowired
    RestTemplate restTemplate;

    public Long createAnAlert(Long plotId, Long deviceId, Date createdDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(Utils.DATE_FORMAT);
        String date = formatter.format(createdDate);

        AlertDTO alert = new AlertDTO(plotId, deviceId, date);
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new GsonBuilder().create();
        String alertJsonObj = gson.toJson(alert);
        HttpEntity<String> request = new HttpEntity<String>(alertJsonObj.toString(), headers);

        AlertResponseDTO response = restTemplate.postForObject("http://alert-service/irrigation/api/v1/alerts", request, AlertResponseDTO.class);
        Utils.log("Successfully created an alert : " + response.getId(), LogLevel.INFO, className);
        return response.getId();
    }
}