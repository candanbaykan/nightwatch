package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentSummary> findAll();

    Optional<DepartmentDetails> findById(Long id);

    Long save(DepartmentRequest departmentRequest);

    Optional<DepartmentDetails> updateById(Long id, DepartmentRequest departmentRequest);

    void deleteById(Long id);

}
