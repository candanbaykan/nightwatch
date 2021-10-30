package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;

import java.util.List;

public interface DemoService {
    List<DemoSummary> findAll();
    Long save(DemoRequest demoRequest);
}
