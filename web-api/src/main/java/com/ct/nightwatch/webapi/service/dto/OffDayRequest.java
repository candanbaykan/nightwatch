package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OffDayRequest {
    private Date begin;
    private Date end;
    private Long employeeId;

    public OffDayRequest() {
    }
}
