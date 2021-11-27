package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {
    private Long id;
    private String username;

    public UserSummary() {
    }
}
