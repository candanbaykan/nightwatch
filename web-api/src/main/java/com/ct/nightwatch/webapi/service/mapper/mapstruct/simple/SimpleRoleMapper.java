package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Role;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToRole;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleRoleMapper {

    RoleSummary toSummary(Role role);

    @IdToRole
    Role toEntity(Long id);

}
