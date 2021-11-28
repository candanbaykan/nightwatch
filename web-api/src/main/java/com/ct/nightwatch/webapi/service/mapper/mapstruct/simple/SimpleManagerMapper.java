package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToManager;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleManagerMapper {

    ManagerSummary toSummary(Manager manager);

    @IdToManager
    Manager toEntity(Long id);

}
