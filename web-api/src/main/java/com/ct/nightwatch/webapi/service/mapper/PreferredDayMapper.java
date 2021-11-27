package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToPreferredDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeMapper.class})
public interface PreferredDayMapper {

    PreferredDaySummary toSummary(PreferredDay preferredDay);

    PreferredDayDetails toDetails(PreferredDay preferredDay);

    @IdToPreferredDay
    PreferredDay toEntity(Long id);

    @Mapping(
            source = "preferredDayRequest." + PreferredDayRequest.Fields.employeeId,
            target = PreferredDay.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    PreferredDay toEntity(PreferredDayRequest preferredDayRequest);

    @Mapping(
            source = "preferredDayRequest." + PreferredDayRequest.Fields.employeeId,
            target = PreferredDay.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    PreferredDay toEntity(Long id, PreferredDayRequest preferredDayRequest);

}
