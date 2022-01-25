package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim;
import com.ct.nightwatch.webapi.repository.ManagerRepository;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.ManagerService;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.ManagerMapper;
import com.ct.nightwatch.webapi.service.specification.ManagerSpecification;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultManagerService implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public DefaultManagerService(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public List<ManagerSummary> findAll(Map<String, String> parameters) {
        ManagerSpecification specification = new ManagerSpecification(parameters);
        return managerRepository.findAll(specification).stream()
                .map(managerMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerDetails findById(Long id) {
        return managerRepository.findDetailsById(id)
                .map(managerMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, Manager.class));
    }

    @Override
    public Long save(@Trim ManagerRequest managerRequest) {
        Manager manager = managerMapper.toEntity(managerRequest);
        return managerRepository.save(manager).getId();
    }

    @Override
    public void updateById(Long id, @Trim ManagerRequest managerRequest) {
        if (!managerRepository.existsById(id))
            throw new EntityNotFoundException(id, Manager.class);

        Manager manager = managerMapper.toEntity(id, managerRequest);
        managerRepository.save(manager);
    }

    @Override
    public void deleteById(Long id) {
        try {
            managerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, Manager.class);
        }
    }

}
