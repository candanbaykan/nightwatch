package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.RoleRepository;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;
import com.ct.nightwatch.webapi.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultRoleService implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public DefaultRoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleSummary> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleSummary> findById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toSummary);
    }

}
