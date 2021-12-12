package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;

import java.util.List;
import java.util.Optional;

public interface PreferredDayService {

    List<PreferredDaySummary> findAll();

    Optional<PreferredDayDetails> findById(Long id);

    Long save(PreferredDayRequest preferredDayRequest);

    void updateById(Long id, PreferredDayRequest preferredDayRequest);

    void deleteById(Long id);

}
