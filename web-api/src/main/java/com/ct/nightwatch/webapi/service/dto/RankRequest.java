package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankRequest {
    private String name;
    private Integer mandatoryDay;
    private Integer level;

    public RankRequest() {
    }
}
