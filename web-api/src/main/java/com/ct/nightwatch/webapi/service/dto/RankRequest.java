package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class RankRequest {

    private String name;
    private Integer mandatoryDay;
    private Integer level;

    public RankRequest() {
    }

}
