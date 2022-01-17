package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;

import java.util.List;
import java.util.Map;

public interface PreferredDayService {

    List<PreferredDayDetails> findAll(Map<String, String> parameters);

    PreferredDayDetails findById(Long id);

    List<PreferredDayDetails> findByDepartmentId(Long departmentId);

    Long save(PreferredDayRequest preferredDayRequest);

    void updateById(Long id, PreferredDayRequest preferredDayRequest);

    void deleteById(Long id);

}
