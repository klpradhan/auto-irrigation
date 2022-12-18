package com.agri.irrigation.alertservice.resource;

import com.agri.irrigation.alertservice.dto.AlertSearchDTO;
import com.agri.irrigation.alertservice.models.Alert;
import com.agri.irrigation.alertservice.services.AlertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Alert Management service endpoints
 * Create and Search alerts
 */
@RestController
@RequestMapping(path="/irrigation/api/v1")
public class AlertResource {

    @Autowired
    AlertInfo alertInfo;

    /**
     * @param alertId
     * @return Device details
     */
    @GetMapping(path = "/alerts/{alertId}")
    public ResponseEntity<Alert> getAlertInfo(@PathVariable("alertId") String alertId) {
        Alert foundAlert = alertInfo.getDetails(Long.valueOf(alertId));
        if (foundAlert == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundAlert);
        }
    }

    /**
     * @param alert
     * @return Newly created Device object
     * @throws URISyntaxException failed to register
     */
    @PostMapping(path = "/alerts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Alert> registerAnAlert(@RequestBody Alert alert)
            throws URISyntaxException {
        Alert createdAlert = alertInfo.register(alert);
        if(createdAlert == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdAlert.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdAlert);
        }
    }

}
