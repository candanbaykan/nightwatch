package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToRole;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {

    UserSummary toSummary(User user);

    UserDetails toDetails(User user);

    @IdToUser
    User toEntity(Long id);

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
