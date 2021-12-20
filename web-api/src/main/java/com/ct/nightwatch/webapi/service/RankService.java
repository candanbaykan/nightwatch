package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;

import java.util.List;

public interface RankService {

    List<RankSummary> findAll();

    RankSummary findById(Long id);

    Long save(RankRequest rankRequest);

    void updateById(Long id, RankRequest rankRequest);

    void deleteById(Long id);

}
