package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;

import java.util.List;
import java.util.Optional;

public interface DemoService {

    List<DemoSummary> findAll();

    Optional<DemoSummary> findById(Long id);

    Long save(DemoRequest demoRequest);

}
