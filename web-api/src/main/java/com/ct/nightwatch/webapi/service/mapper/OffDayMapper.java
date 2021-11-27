package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToOffDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeMapper.class})
public interface OffDayMapper {

    OffDaySummary toSummary(OffDay offDay);

    OffDayDetails toDetails(OffDay offDay);

    @IdToOffDay
    OffDay toEntity(Long id);

    @Mapping(
            source = "offDayRequest." + OffDayRequest.Fields.employeeId,
            target = OffDay.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    OffDay toEntity(OffDayRequest offDayRequest);

    @Mapping(
            source = "offDayRequest." + OffDayRequest.Fields.employeeId,
            target = OffDay.Fields.employee,
            qualifiedBy = IdToEmployee.class
    )
    OffDay toEntity(Long id, OffDayRequest offDayRequest);
}
