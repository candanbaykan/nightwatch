package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;

import java.util.List;

public interface ManagerService {

    List<ManagerSummary> findAll();

    ManagerDetails findById(Long id);

    Long save(ManagerRequest managerRequest);

    void updateById(Long id, ManagerRequest managerRequest);

    void deleteById(Long id);

}
