package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class WatchRequest {
    private Date date;
    private Long employeeId;

    public WatchRequest() {
    }
}
