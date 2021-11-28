package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToPreferredDay;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimplePreferredDayMapper {

    PreferredDaySummary toSummary(PreferredDay preferredDay);

    @IdToPreferredDay
    PreferredDay toEntity(Long id);

}
