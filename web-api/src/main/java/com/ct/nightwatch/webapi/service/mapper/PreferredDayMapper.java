package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;

public interface PreferredDayMapper {

    PreferredDaySummary toSummary(PreferredDay preferredDay);

    PreferredDayDetails toDetails(PreferredDay preferredDay);

    PreferredDay toEntity(Long id);

    PreferredDay toEntity(PreferredDayRequest preferredDayRequest);

    PreferredDay toEntity(Long id, PreferredDayRequest preferredDayRequest);

}
