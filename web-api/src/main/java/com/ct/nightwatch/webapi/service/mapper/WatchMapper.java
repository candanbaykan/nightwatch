package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToWatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeMapper.class})
public interface WatchMapper {

    WatchSummary toSummary(Watch watch);

    WatchDetails toDetails(Watch watch);

    @IdToWatch
    Watch toEntity(Long id);

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
