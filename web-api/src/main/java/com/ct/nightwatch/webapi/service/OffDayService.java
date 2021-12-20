package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;

import java.util.List;

public interface OffDayService {

    List<OffDaySummary> findAll();

    OffDayDetails findById(Long id);

    Long save(OffDayRequest offDayRequest);

    void updateById(Long id, OffDayRequest offDayRequest);

    void deleteById(Long id);

}
