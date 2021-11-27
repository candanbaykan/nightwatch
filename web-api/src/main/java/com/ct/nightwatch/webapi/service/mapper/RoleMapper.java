package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Role;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToRole;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleSummary toSummary(Role role);

    @IdToRole
    Role toEntity(Long id);

}
