package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Rank;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToRank;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleRankMapper {

    RankSummary toSummary(Rank rank);

    @IdToRank
    Rank toEntity(Long id);

    Rank toEntity(RankRequest rankRequest);

    Rank toEntity(Long id, RankRequest rankRequest);

}
