package com.ct.nightwatch.webapi.service.mapper.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
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
public interface ComplexOffDayMapper {

    OffDayDetails toDetails(OffDay offDay);

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
