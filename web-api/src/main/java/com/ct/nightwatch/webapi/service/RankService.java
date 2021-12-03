package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;

import java.util.List;
import java.util.Optional;

public interface RankService {

    List<RankSummary> findAll();

    Optional<RankSummary> findById(Long id);

    Long save(RankRequest rankRequest);

    Optional<RankSummary> updateById(Long id, RankRequest rankRequest);

    void deleteById(Long id);

}
