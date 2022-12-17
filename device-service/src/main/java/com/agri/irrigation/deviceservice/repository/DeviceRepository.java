package com.agri.irrigation.deviceservice.repository;

import com.agri.irrigation.deviceservice.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
