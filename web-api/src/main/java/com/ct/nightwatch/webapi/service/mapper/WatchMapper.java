package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;

public interface WatchMapper {

    WatchSummary toSummary(Watch watch);

    WatchDetails toDetails(Watch watch);

    Watch toEntity(Long id);

    Watch toEntity(WatchRequest watchRequest);

    Watch toEntity(Long id, WatchRequest watchRequest);

}
