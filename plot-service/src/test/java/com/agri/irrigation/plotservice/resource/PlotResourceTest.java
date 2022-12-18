package com.agri.irrigation.plotservice.resource;

import com.agri.irrigation.plotservice.dto.PlotDTO;
import com.agri.irrigation.plotservice.services.PlotInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit test cases for Plot Service functionalities")
class PlotResourceTest {

    @Autowired
    PlotInfo plotInfo;

    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @Nested
    @DisplayName("Check plot existence")
    @Tag("PlotCheck")
    class CheckPlot {

        @Test
        @DisplayName("when testing with existing plot")
        void getExistingPlotInfo() {
            Long plotId = 14L;
            PlotDTO foundPlot = plotInfo.getDetails(plotId);
            assertNotNull(foundPlot);
        }

        @Test
        @DisplayName("when testing with non existing plot")
        void getNonExistentPlotInfo() {
            Long plotId = 1001L;
            PlotDTO foundPlot = plotInfo.getDetails(plotId);
            assertNotNull(foundPlot);
        }

    }

    @Nested
    @DisplayName("Update plot info")
    @Tag("PlotUpdate")
    class UpdatePlot {
        @Test
        @DisplayName("when testing with existing device")
        void addPlotInfo() {
            Long plotId = 14L;

        }

        @Test
        @DisplayName("when testing with existing device")
        void updatePlotInfo() {
            Long plotId = 14L;
        }

        @Test
        @DisplayName("when testing with existing device")
        void deletePlot() {

        }
    }

}