package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import com.ct.nightwatch.webapi.service.mapper.UserMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex.ComplexUserMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleUserMapper;
import org.springframework.stereotype.Component;

@Component
public class MsUserMapper implements UserMapper {

    private final SimpleUserMapper simpleUserMapper;
    private final ComplexUserMapper complexUserMapper;

    public MsUserMapper(SimpleUserMapper simpleUserMapper, ComplexUserMapper complexUserMapper) {
        this.simpleUserMapper = simpleUserMapper;
        this.complexUserMapper = complexUserMapper;
    }

    @Override
    public UserSummary toSummary(User user) {
        return simpleUserMapper.toSummary(user);
    }

    @Override
    public UserDetails toDetails(User user) {
        return complexUserMapper.toDetails(user);
    }

    @Override
    public User toEntity(Long id) {
        return simpleUserMapper.toEntity(id);
    }

    @Override
    public User toEntity(UserRequest userRequest) {
        return complexUserMapper.toEntity(userRequest);
    }

    @Override
    public User toEntity(Long id, UserRequest userRequest) {
        return complexUserMapper.toEntity(id, userRequest);
    }

}
