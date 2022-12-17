package com.agri.irrigation.alertservice.repository;

import com.agri.irrigation.alertservice.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
