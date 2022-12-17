package com.agri.irrigation.alertservice.services;

import com.agri.irrigation.alertservice.models.Alert;
import com.agri.irrigation.alertservice.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertInfo {

    @Autowired
    AlertRepository alertRepository;

    public Alert getDetails(Long alertId) {
        return alertRepository.findById(alertId).orElse(null);
    }


    public Alert register(Alert alert) {
        try{
            alertRepository.save(alert);
        } catch (Exception e) {

        } finally {
            return alertRepository.findById(alert.getId()).orElse(null);
        }

    }
}
