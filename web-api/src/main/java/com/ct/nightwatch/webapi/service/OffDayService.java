package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;

import java.util.List;
import java.util.Map;

public interface OffDayService {

    List<OffDayDetails> findAll(Map<String, String> parameters);

    OffDayDetails findById(Long id);

    List<OffDayDetails> findByDepartmentId(Long departmentId);

    Long save(OffDayRequest offDayRequest);

    void updateById(Long id, OffDayRequest offDayRequest);

    void deleteById(Long id);

}
