package com.agri.irrigation.plotservice.repository;

import com.agri.irrigation.plotservice.dto.PlotDTO;
import com.agri.irrigation.plotservice.models.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlotRepository extends JpaRepository<Plot, Long> {
}
