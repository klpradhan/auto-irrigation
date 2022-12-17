package com.agri.irrigation.irrigationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PlotDTO {
    private Long slotsTime;
    private String cropType;
    private Float area;
    private DeviceDTO device;
    private Date createdDate;
    private Date modifiedDate;

    public PlotDTO(Long slotsTime, String cropType, Float area, DeviceDTO device, Date createdDate, Date modifiedDate) {
        this.slotsTime = slotsTime;
        this.cropType = cropType;
        this.area = area;
        this.device = device;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "PlotDTO{" +
                "slotsTime=" + slotsTime +
                ", cropType='" + cropType + '\'' +
                ", area=" + area +
                ", device=" + device +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
