package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleEmployeeMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {SimpleEmployeeMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexPreferredDayMapper {

    PreferredDayDetails toDetails(PreferredDay preferredDay);

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
