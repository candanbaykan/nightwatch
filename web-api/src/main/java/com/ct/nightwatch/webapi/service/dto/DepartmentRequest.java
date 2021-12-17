package com.ct.nightwatch.webapi.service.dto;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trimmable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
@Trimmable
public class DepartmentRequest {

    private String name;
    private Integer dailyWatch;
    private Long managerId;

    public DepartmentRequest() {
    }

}
