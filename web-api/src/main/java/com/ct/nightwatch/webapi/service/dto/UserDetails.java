package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {
    private Long id;
    private String username;
    private RoleSummary role;

    public UserDetails() {
    }
}
