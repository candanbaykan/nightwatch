package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PreferredDayRequest {
    private Date date;
    private Long employeeId;

    public PreferredDayRequest() {
    }
}
