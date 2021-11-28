package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToWatch;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleWatchMapper {

    WatchSummary toSummary(Watch watch);

    @IdToWatch
    Watch toEntity(Long id);

}
