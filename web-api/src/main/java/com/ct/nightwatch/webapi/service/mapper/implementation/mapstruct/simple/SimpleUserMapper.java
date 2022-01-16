package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.qualifier.IdToUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleUserMapper {

    UserSummary toSummary(User user);

    @IdToUser
    User toEntity(Long id);

}
