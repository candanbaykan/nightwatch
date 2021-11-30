package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.RoleSummary;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleSummary> findAll();

    Optional<RoleSummary> findById(Long id);

}
