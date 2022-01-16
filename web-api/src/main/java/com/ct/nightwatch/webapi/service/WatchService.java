package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;

import java.util.List;
import java.util.Optional;

public interface WatchService {

    List<WatchDetails> findAll();

    WatchDetails findById(Long id);

    Long save(WatchRequest watchRequest);

    void updateById(Long id, WatchRequest watchRequest);

    void deleteById(Long id);

}
