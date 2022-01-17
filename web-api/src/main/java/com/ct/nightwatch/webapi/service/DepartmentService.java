package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentListItem;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<DepartmentListItem> findAll(Map<String, String> parameters);

    DepartmentDetails findById(Long id);

    Long save(DepartmentRequest departmentRequest);

    void updateById(Long id, DepartmentRequest departmentRequest);

    void deleteById(Long id);

}
