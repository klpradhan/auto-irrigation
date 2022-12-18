package com.agri.irrigation.plotservice.resource;

import com.agri.irrigation.plotservice.dto.PlotDTO;
import com.agri.irrigation.plotservice.exceptions.DeviceAlreadyUsedException;
import com.agri.irrigation.plotservice.exceptions.DeviceNotFoundException;
import com.agri.irrigation.plotservice.models.Plot;
import com.agri.irrigation.plotservice.services.PlotInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Plot Land Management service endpoints
 */
@RestController
@RequestMapping(path="/irrigation/api/v1")
public class PlotResource {
    
    @Autowired
    PlotInfo plotInfo;

    /**
     * @param plotId plot id (can be replaced with new PLot Serial Number instead of database identity)
     * @return plot details
     */
    @GetMapping(path = "/plots/{plotId}")
    public ResponseEntity<PlotDTO> getPlotInfo(@PathVariable("plotId") String plotId) {
        PlotDTO foundPlot = plotInfo.getDetails(Long.valueOf(plotId));
        if (foundPlot == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundPlot);
        }
    }

    /**
     *
     * @param plot plot id (can be replaced with new PLot Serial Number instead of database identity)
     * @return Newly created Plot
     * @throws URISyntaxException failed to register
     */
    @PostMapping(path = "/plots")
    public ResponseEntity<Plot> addPlotInfo(@RequestBody Plot plot)
            throws URISyntaxException {
        Plot registeredPlot = null;
        try {
            registeredPlot = plotInfo.register(plot);
        } catch (DeviceAlreadyUsedException | DeviceNotFoundException dane) {

        }

        if(registeredPlot == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(registeredPlot.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(registeredPlot);
        }
    }

    /**
     *
     * @param plot plot details to update
     * @param plotId plot id (can be replaced with new PLot Serial Number instead of database identity)
     * @return Updated device object
     */
    @PutMapping("/plots/{plotId}")
    public ResponseEntity<Plot> updatePlotInfo(@RequestBody Plot plot, @PathVariable Long plotId) {
        Plot editedPlot = null;
        try {
            editedPlot = plotInfo.register(plot);
        } catch (DeviceAlreadyUsedException | DeviceNotFoundException dane) {
            return ResponseEntity.notFound().build();
        }
        if (editedPlot == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(editedPlot);
        }
    }

    /**
     *
     * @param plotId plot id (can be replaced with new PLot Serial Number instead of database identity)
     * @return status
     */
    @DeleteMapping("/plots/{plotId}")
    public ResponseEntity<Object> deletePlot(@PathVariable Long plotId) {
        plotInfo.remove(plotId);
        return ResponseEntity.noContent().build();
    }
}
