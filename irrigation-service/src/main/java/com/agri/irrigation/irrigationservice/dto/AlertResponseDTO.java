package com.agri.irrigation.irrigationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AlertResponseDTO {
    private Long id;
    private Long plotID;
    private Long deviceId;
    private Date createdDate;
}
