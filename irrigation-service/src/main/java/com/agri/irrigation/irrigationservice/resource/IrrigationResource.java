package com.agri.irrigation.irrigationservice.resource;

import com.agri.irrigation.irrigationservice.models.IrrigationAudit;
import com.agri.irrigation.irrigationservice.services.IrrigationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Automatic Irrigation System - services
 */
@RestController
@RequestMapping(path="/irrigation/api/v1")
public class IrrigationResource {

    @Autowired
    IrrigationInfo irrigationInfo;

    /**
     * Retry "retry" times, calling Device check service
     */
    @Value("${irrigation.retry}")
    private String retry;

    /**
     * Retry after retryDelay secs
     */
    @Value("${irrigation.retry.delay}")
    private String retryDelay;

    /**
     * Start Irrigation on a plot of land
     */
    @PostMapping(path = "/irrigation/{plotId}")
    public ResponseEntity<IrrigationAudit> irrigatePlot(@PathVariable("plotId") String plotId)
            throws URISyntaxException {
        IrrigationAudit response = irrigationInfo.irrigate(Long.valueOf(plotId), Integer.valueOf(retry), Integer.valueOf(retryDelay));
        if(response == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(response);
        }
    }
}
