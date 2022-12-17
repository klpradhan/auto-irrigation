package com.agri.irrigation.plotservice.resource;

import com.agri.irrigation.plotservice.dto.PlotDTO;
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
     * @param plotId
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
     * @param plot
     * @return Newly created Plot
     * @throws URISyntaxException
     */
    @PostMapping(path = "/plots")
    public ResponseEntity<Plot> addPlotInfo(@RequestBody Plot plot)
            throws URISyntaxException {
        Plot registeredPlot = plotInfo.register(plot);
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
     * @param plot
     * @param plotId
     * @return Updated device object
     */
    @PutMapping("/plots/{plotId}")
    public ResponseEntity<Plot> updatePlotInfo(@RequestBody Plot plot, @PathVariable Long plotId) {
        Plot editedPlot = plotInfo.updateDetails(plot);
        if (editedPlot == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(editedPlot);
        }
    }

    /**
     *
     * @param plotId
     * @return status
     */
    @DeleteMapping("/plots/{plotId}")
    public ResponseEntity<Object> deletePlot(@PathVariable Long plotId) {
        plotInfo.remove(plotId);
        return ResponseEntity.noContent().build();
    }
}
