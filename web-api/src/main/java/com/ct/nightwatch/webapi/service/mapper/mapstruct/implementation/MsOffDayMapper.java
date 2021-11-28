package com.ct.nightwatch.webapi.service.mapper.mapstruct.implementation;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import com.ct.nightwatch.webapi.service.mapper.OffDayMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.complex.ComplexOffDayMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleOffDayMapper;
import org.springframework.stereotype.Component;

@Component
public class MsOffDayMapper implements OffDayMapper {

    private final SimpleOffDayMapper simpleOffDayMapper;
    private final ComplexOffDayMapper complexOffDayMapper;

    public MsOffDayMapper(SimpleOffDayMapper simpleOffDayMapper, ComplexOffDayMapper complexOffDayMapper) {
        this.simpleOffDayMapper = simpleOffDayMapper;
        this.complexOffDayMapper = complexOffDayMapper;
    }

    @Override
    public OffDaySummary toSummary(OffDay offDay) {
        return simpleOffDayMapper.toSummary(offDay);
    }

    @Override
    public OffDayDetails toDetails(OffDay offDay) {
        return complexOffDayMapper.toDetails(offDay);
    }

    @Override
    public OffDay toEntity(Long id) {
        return simpleOffDayMapper.toEntity(id);
    }

    @Override
    public OffDay toEntity(OffDayRequest offDayRequest) {
        return complexOffDayMapper.toEntity(offDayRequest);
    }

    @Override
    public OffDay toEntity(Long id, OffDayRequest offDayRequest) {
        return complexOffDayMapper.toEntity(id, offDayRequest);
    }

}
