package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter
@Setter
@FieldNameConstants
public class PreferredDayRequest {

    private LocalDate date;
    private Long employeeId;

    public PreferredDayRequest() {
    }

}
