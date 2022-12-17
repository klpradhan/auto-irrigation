package com.agri.irrigation.irrigationservice.repository;

import com.agri.irrigation.irrigationservice.models.IrrigationAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationAuditRepository extends JpaRepository<IrrigationAudit, Long> {
}
