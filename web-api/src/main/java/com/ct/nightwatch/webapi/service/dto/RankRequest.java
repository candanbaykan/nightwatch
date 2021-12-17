package com.ct.nightwatch.webapi.service.dto;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trimmable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
@Trimmable
public class RankRequest {

    private String name;
    private Integer mandatoryDay;
    private Integer level;

    public RankRequest() {
    }

}
