package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter
@Setter
@FieldNameConstants
public class WatchRequest {

    private LocalDate date;
    private Long employeeId;

    public WatchRequest() {
    }

}
