package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Demo;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import org.mapstruct.Mapper;

@Mapper
public interface DemoMapper {
    DemoSummary toSummary(Demo demo);
}
