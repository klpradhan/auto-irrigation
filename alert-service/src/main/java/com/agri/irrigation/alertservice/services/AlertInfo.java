package com.agri.irrigation.alertservice.services;

import com.agri.irrigation.alertservice.dto.AlertSearchDTO;
import com.agri.irrigation.alertservice.models.Alert;
import com.agri.irrigation.alertservice.repository.AlertRepository;
import com.agri.irrigation.alertservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertInfo {
    private static final String className = AlertInfo.class.getName();

    @Autowired
    AlertRepository alertRepository;

    public Alert getDetails(Long alertId) {
        return alertRepository.findById(alertId).orElse(null);
    }


    public Alert register(Alert alert) {
        try{
            alertRepository.save(alert);
            Utils.log("Successfully registered the alert.", LogLevel.INFO, className);
        } catch (DataAccessException e) {
            Utils.log("Failed to register the alert.", LogLevel.ERROR, className);
            Utils.log(e.getMessage(), LogLevel.ERROR, className);
        }
        return alertRepository.findById(alert.getId()).orElse(null);
    }

}
