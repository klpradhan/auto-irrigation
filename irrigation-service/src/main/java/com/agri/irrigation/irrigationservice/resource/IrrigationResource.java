package com.agri.irrigation.irrigationservice.resource;

import com.agri.irrigation.irrigationservice.models.IrrigationAudit;
import com.agri.irrigation.irrigationservice.services.IrrigationInfo;
import com.agri.irrigation.irrigationservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
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
    private static final String className = IrrigationResource.class.getName();

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
        IrrigationAudit response = null;
        try {
            response = irrigationInfo.irrigate(Long.valueOf(plotId), Integer.valueOf(retry), Integer.valueOf(retryDelay));
        } catch (NumberFormatException ne) {
            Utils.log(ne.getMessage(), LogLevel.ERROR, className);
            return ResponseEntity.noContent().build();
        } catch (InterruptedException e) {
            return ResponseEntity.noContent().build();
        }

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
