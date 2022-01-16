package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;
import com.ct.nightwatch.webapi.service.mapper.PreferredDayMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex.ComplexPreferredDayMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimplePreferredDayMapper;
import org.springframework.stereotype.Component;

@Component
public class MsPreferredDayMapper implements PreferredDayMapper {

    private final SimplePreferredDayMapper simplePreferredDayMapper;
    private final ComplexPreferredDayMapper complexPreferredDayMapper;

    public MsPreferredDayMapper(SimplePreferredDayMapper simplePreferredDayMapper, ComplexPreferredDayMapper complexPreferredDayMapper) {
        this.simplePreferredDayMapper = simplePreferredDayMapper;
        this.complexPreferredDayMapper = complexPreferredDayMapper;
    }

    @Override
    public PreferredDaySummary toSummary(PreferredDay preferredDay) {
        return simplePreferredDayMapper.toSummary(preferredDay);
    }

    @Override
    public PreferredDayDetails toDetails(PreferredDay preferredDay) {
        return complexPreferredDayMapper.toDetails(preferredDay);
    }

    @Override
    public PreferredDay toEntity(Long id) {
        return simplePreferredDayMapper.toEntity(id);
    }

    @Override
    public PreferredDay toEntity(PreferredDayRequest preferredDayRequest) {
        return complexPreferredDayMapper.toEntity(preferredDayRequest);
    }

    @Override
    public PreferredDay toEntity(Long id, PreferredDayRequest preferredDayRequest) {
        return complexPreferredDayMapper.toEntity(id, preferredDayRequest);
    }

}
