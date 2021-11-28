package com.ct.nightwatch.webapi.service.mapper.mapstruct.implementation;

import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import com.ct.nightwatch.webapi.service.mapper.ManagerMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.complex.ComplexManagerMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleManagerMapper;
import org.springframework.stereotype.Component;

@Component
public class MsManagerMapper implements ManagerMapper {

    private final SimpleManagerMapper simpleManagerMapper;
    private final ComplexManagerMapper complexManagerMapper;

    public MsManagerMapper(SimpleManagerMapper simpleManagerMapper, ComplexManagerMapper complexManagerMapper) {
        this.simpleManagerMapper = simpleManagerMapper;
        this.complexManagerMapper = complexManagerMapper;
    }

    @Override
    public ManagerSummary toSummary(Manager manager) {
        return simpleManagerMapper.toSummary(manager);
    }

    @Override
    public ManagerDetails toDetails(Manager manager) {
        return complexManagerMapper.toDetails(manager);
    }

    @Override
    public Manager toEntity(Long id) {
        return simpleManagerMapper.toEntity(id);
    }

    @Override
    public Manager toEntity(ManagerRequest managerRequest) {
        return complexManagerMapper.toEntity(managerRequest);
    }

    @Override
    public Manager toEntity(Long id, ManagerRequest managerRequest) {
        return complexManagerMapper.toEntity(id, managerRequest);
    }

}
