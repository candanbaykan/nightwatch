package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.qualifier.IdToRole;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleRoleMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {SimpleRoleMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexUserMapper {

    UserDetails toDetails(User user);

    @Mapping(
            source = "userRequest." + UserRequest.Fields.roleId,
            target = User.Fields.role,
            qualifiedBy = IdToRole.class
    )
    User toEntity(UserRequest userRequest);

    @Mapping(
            source = "userRequest." + UserRequest.Fields.roleId,
            target = User.Fields.role,
            qualifiedBy = IdToRole.class
    )
    User toEntity(Long id, UserRequest userRequest);

}
