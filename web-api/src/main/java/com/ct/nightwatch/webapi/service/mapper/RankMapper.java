package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Rank;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;

public interface RankMapper {

    RankSummary toSummary(Rank rank);

    Rank toEntity(Long id);

    Rank toEntity(RankRequest rankRequest);

    Rank toEntity(Long id, RankRequest rankRequest);

}
