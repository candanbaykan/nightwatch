package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToOffDay;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleOffDayMapper {

    OffDaySummary toSummary(OffDay offDay);

    @IdToOffDay
    OffDay toEntity(Long id);

}
