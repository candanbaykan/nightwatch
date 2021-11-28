package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;

public interface OffDayMapper {

    OffDaySummary toSummary(OffDay offDay);

    OffDayDetails toDetails(OffDay offDay);

    OffDay toEntity(Long id);

    OffDay toEntity(OffDayRequest offDayRequest);

    OffDay toEntity(Long id, OffDayRequest offDayRequest);
}
