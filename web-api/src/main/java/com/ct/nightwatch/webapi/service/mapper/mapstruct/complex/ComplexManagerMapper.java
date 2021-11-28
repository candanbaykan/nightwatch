package com.ct.nightwatch.webapi.service.mapper.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToUser;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleDepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleUserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {SimpleDepartmentMapper.class, SimpleUserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexManagerMapper {

    ManagerDetails toDetails(Manager manager);

    @Mapping(
            source = "managerRequest." + ManagerRequest.Fields.userId,
            target = Manager.Fields.user,
            qualifiedBy = IdToUser.class
    )
    Manager toEntity(ManagerRequest managerRequest);

    @Mapping(
            source = "managerRequest." + ManagerRequest.Fields.userId,
            target = Manager.Fields.user,
            qualifiedBy = IdToUser.class
    )
    Manager toEntity(Long id, ManagerRequest managerRequest);

}
