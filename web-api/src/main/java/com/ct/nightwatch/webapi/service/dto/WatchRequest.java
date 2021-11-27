package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.sql.Date;

@Getter
@Setter
@FieldNameConstants
public class WatchRequest {

    private Date date;
    private Long employeeId;

    public WatchRequest() {
    }

}
