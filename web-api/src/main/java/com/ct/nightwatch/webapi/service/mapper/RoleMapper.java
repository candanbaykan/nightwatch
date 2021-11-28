package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Role;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;

public interface RoleMapper {

    RoleSummary toSummary(Role role);

    Role toEntity(Long id);

}
