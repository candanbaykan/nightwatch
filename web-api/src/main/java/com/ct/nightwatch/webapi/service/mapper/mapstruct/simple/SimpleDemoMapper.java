package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Demo;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleDemoMapper {

    DemoSummary toSummary(Demo demo);

    Demo toEntity(DemoRequest demoRequest);

}
