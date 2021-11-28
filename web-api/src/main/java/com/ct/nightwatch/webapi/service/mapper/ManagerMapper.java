package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;

public interface ManagerMapper {

    ManagerSummary toSummary(Manager manager);

    ManagerDetails toDetails(Manager manager);

    Manager toEntity(Long id);

    Manager toEntity(ManagerRequest managerRequest);

    Manager toEntity(Long id, ManagerRequest managerRequest);

}
