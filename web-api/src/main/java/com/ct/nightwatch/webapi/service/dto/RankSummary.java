package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankSummary {
    private Long id;
    private String name;
    private Integer mandatoryDay;
    private Integer level;

    public RankSummary() {
    }
}
