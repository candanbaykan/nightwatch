package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OffDaySummary {

    private Long id;
    private Date begin;
    private Date end;

    public OffDaySummary() {
    }

}
