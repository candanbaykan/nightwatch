package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Demo;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;

public interface DemoMapper {

    DemoSummary toSummary(Demo demo);

    Demo toEntity(DemoRequest demoRequest);

}
