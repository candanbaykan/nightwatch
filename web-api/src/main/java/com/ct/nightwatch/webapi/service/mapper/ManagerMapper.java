package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToManager;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DepartmentMapper.class, UserMapper.class})
public interface ManagerMapper {

    ManagerSummary toSummary(Manager manager);

    ManagerDetails toDetails(Manager manager);

    @IdToManager
    Manager toEntity(Long id);

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
