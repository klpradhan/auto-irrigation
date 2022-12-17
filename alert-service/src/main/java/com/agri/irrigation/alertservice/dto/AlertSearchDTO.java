package com.agri.irrigation.alertservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AlertSearchDTO {
    private Long plotID;
    private Long deviceId;
    private Date startDate;
    private Date endDate;

    public AlertSearchDTO(Long plotID, Long deviceId, Date startDate, Date endDate) {
        this.plotID = plotID;
        this.deviceId = deviceId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AlertSearchDTO{" +
                "plotID=" + plotID +
                ", deviceId=" + deviceId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
