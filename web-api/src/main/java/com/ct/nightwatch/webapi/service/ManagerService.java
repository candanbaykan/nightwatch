package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    List<ManagerSummary> findAll(Map<String, String> parameters);

    ManagerDetails findById(Long id);

    Long save(ManagerRequest managerRequest);

    void updateById(Long id, ManagerRequest managerRequest);

    void deleteById(Long id);

}
