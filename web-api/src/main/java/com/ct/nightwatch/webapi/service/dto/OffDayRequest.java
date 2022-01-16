package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter
@Setter
@FieldNameConstants
public class OffDayRequest {

    private LocalDate begin;
    private LocalDate end;
    private Long employeeId;

    public OffDayRequest() {
    }

}
