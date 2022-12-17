package com.agri.irrigation.plotservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {
    private String serialNum;
    private String status;
    private String availability;
    private String alertEmail;
    private String alertPhone;
    private Date createdDate;
    private Date modifiedDate;

    public DeviceDTO(String serialNum, String status, String availability, String alertEmail, String alertPhone, Date createdDate, Date modifiedDate) {
        this.serialNum = serialNum;
        this.status = status;
        this.availability = availability;
        this.alertEmail = alertEmail;
        this.alertPhone = alertPhone;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "serialNum='" + serialNum + '\'' +
                ", status='" + status + '\'' +
                ", availability='" + availability + '\'' +
                ", alertEmail='" + alertEmail + '\'' +
                ", alertPhone='" + alertPhone + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
