package com.ct.nightwatch.webapi.service.mapper.mapstruct.implementation;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;
import com.ct.nightwatch.webapi.service.mapper.WatchMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.complex.ComplexWatchMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleWatchMapper;
import org.springframework.stereotype.Component;

@Component
public class MsWatchMapper implements WatchMapper {

    private final SimpleWatchMapper simpleWatchMapper;
    private final ComplexWatchMapper complexWatchMapper;

    public MsWatchMapper(SimpleWatchMapper simpleWatchMapper, ComplexWatchMapper complexWatchMapper) {
        this.simpleWatchMapper = simpleWatchMapper;
        this.complexWatchMapper = complexWatchMapper;
    }

    @Override
    public WatchSummary toSummary(Watch watch) {
        return simpleWatchMapper.toSummary(watch);
    }

    @Override
    public WatchDetails toDetails(Watch watch) {
        return complexWatchMapper.toDetails(watch);
    }

    @Override
    public Watch toEntity(Long id) {
        return simpleWatchMapper.toEntity(id);
    }

    @Override
    public Watch toEntity(WatchRequest watchRequest) {
        return complexWatchMapper.toEntity(watchRequest);
    }

    @Override
    public Watch toEntity(Long id, WatchRequest watchRequest) {
        return complexWatchMapper.toEntity(id, watchRequest);
    }

}
