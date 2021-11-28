package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;

public interface UserMapper {

    UserSummary toSummary(User user);

    UserDetails toDetails(User user);

    User toEntity(Long id);

    User toEntity(UserRequest userRequest);

    User toEntity(Long id, UserRequest userRequest);

}
