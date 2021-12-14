package com.ct.nightwatch.webapi.service.dto;

import com.ct.nightwatch.webapi.service.trimmer.annotation.NotTrimmable;
import com.ct.nightwatch.webapi.service.trimmer.annotation.Trimmable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
@Trimmable
public class UserRequest {

    private String username;

    @NotTrimmable
    private String password;

    private Long roleId;

    public UserRequest() {
    }

}
