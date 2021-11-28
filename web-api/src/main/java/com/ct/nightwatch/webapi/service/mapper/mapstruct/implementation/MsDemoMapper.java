package com.ct.nightwatch.webapi.service.mapper.mapstruct.implementation;

import com.ct.nightwatch.webapi.repository.entity.Demo;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import com.ct.nightwatch.webapi.service.mapper.DemoMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleDemoMapper;
import org.springframework.stereotype.Component;

@Component
public class MsDemoMapper implements DemoMapper {

    private final SimpleDemoMapper simpleDemoMapper;

    public MsDemoMapper(SimpleDemoMapper simpleDemoMapper) {
        this.simpleDemoMapper = simpleDemoMapper;
    }

    @Override
    public DemoSummary toSummary(Demo demo) {
        return simpleDemoMapper.toSummary(demo);
    }

    @Override
    public Demo toEntity(DemoRequest demoRequest) {
        return simpleDemoMapper.toEntity(demoRequest);
    }

}
