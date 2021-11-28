package com.ct.nightwatch.webapi.service.mapper.mapstruct.implementation;

import com.ct.nightwatch.webapi.repository.entity.Role;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;
import com.ct.nightwatch.webapi.service.mapper.RoleMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleRoleMapper;
import org.springframework.stereotype.Component;

@Component
public class MsRoleMapper implements RoleMapper {

    private final SimpleRoleMapper simpleRoleMapper;

    public MsRoleMapper(SimpleRoleMapper simpleRoleMapper) {
        this.simpleRoleMapper = simpleRoleMapper;
    }

    @Override
    public RoleSummary toSummary(Role role) {
        return simpleRoleMapper.toSummary(role);
    }

    @Override
    public Role toEntity(Long id) {
        return simpleRoleMapper.toEntity(id);
    }

}
