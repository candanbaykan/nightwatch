package com.ct.nightwatch.webapi.service.mapper.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleEmployeeMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {SimpleEmployeeMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexWatchMapper {

    WatchDetails toDetails(Watch watch);

    @Mapping(
            source = "watchRequest." + WatchRequest.Fields.employeeId,
            target = Watch.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    Watch toEntity(WatchRequest watchRequest);

    @Mapping(
            source = "watchRequest." + WatchRequest.Fields.employeeId,
            target = Watch.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    Watch toEntity(Long id, WatchRequest watchRequest);

}
