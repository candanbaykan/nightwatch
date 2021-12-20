package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.RoleSummary;

import java.util.List;

public interface RoleService {

    List<RoleSummary> findAll();

    RoleSummary findById(Long id);

}
