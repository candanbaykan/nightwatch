package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PreferredDaySummary {
    private Long id;
    private Date date;

    public PreferredDaySummary() {
    }
}
