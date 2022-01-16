package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.Rank;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;
import com.ct.nightwatch.webapi.service.mapper.RankMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleRankMapper;
import org.springframework.stereotype.Component;

@Component
public class MsRankMapper implements RankMapper {

    private final SimpleRankMapper simpleRankMapper;

    public MsRankMapper(SimpleRankMapper simpleRankMapper) {
        this.simpleRankMapper = simpleRankMapper;
    }

    @Override
    public RankSummary toSummary(Rank rank) {
        return simpleRankMapper.toSummary(rank);
    }

    @Override
    public Rank toEntity(Long id) {
        return simpleRankMapper.toEntity(id);
    }

    @Override
    public Rank toEntity(RankRequest rankRequest) {
        return simpleRankMapper.toEntity(rankRequest);
    }

    @Override
    public Rank toEntity(Long id, RankRequest rankRequest) {
        return simpleRankMapper.toEntity(id, rankRequest);
    }

}
