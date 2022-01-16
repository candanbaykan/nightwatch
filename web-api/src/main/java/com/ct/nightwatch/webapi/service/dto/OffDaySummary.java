package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OffDaySummary {

    private Long id;
    private LocalDate begin;
    private LocalDate end;

    public OffDaySummary() {
    }

}
