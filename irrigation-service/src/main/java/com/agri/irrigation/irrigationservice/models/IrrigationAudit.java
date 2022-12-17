package com.agri.irrigation.irrigationservice.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "irrigation_audit")
public class IrrigationAudit {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "plot_id", nullable = false)
    private Long plotId;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "slots_time", nullable = false)
    private Long slotsTime;

    private String status;

    @Column(name = "alert_id")
    private Long alertId;

    private String message;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date startTime = new Date();

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date endTime = new Date();

    public IrrigationAudit(Long plotId, Long deviceId, Long slotsTime, String status, Long alertId, String message, Date startTime, Date endTime) {
        this.plotId = plotId;
        this.deviceId = deviceId;
        this.slotsTime = slotsTime;
        this.status = status;
        this.alertId = alertId;
        this.message = message;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
