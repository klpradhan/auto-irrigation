package com.agri.irrigation.alertservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AlertDTO {
    private Long plotID;
    private Long deviceId;
    private Date createdDate;

    public AlertDTO(Long plotID, Long deviceId, Date createdDate) {
        this.plotID = plotID;
        this.deviceId = deviceId;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AlertDTO{" +
                "plotID=" + plotID +
                ", deviceId=" + deviceId +
                ", createdDate=" + createdDate +
                '}';
    }
}
