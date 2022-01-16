package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PreferredDaySummary {

    private Long id;
    private LocalDate date;

    public PreferredDaySummary() {
    }

}
