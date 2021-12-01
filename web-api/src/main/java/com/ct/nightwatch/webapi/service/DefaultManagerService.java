package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.ManagerRepository;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.ManagerMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public List<ManagerSummary> findAll() {
        return managerRepository.findAll().stream()
                .map(managerMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ManagerDetails> findById(Long id) {
        return managerRepository.findDetailsById(id)
                .map(managerMapper::toDetails);
    }

    @Override
    public Long save(ManagerRequest managerRequest) {
        return managerRepository.save(managerMapper.toEntity(managerRequest)).getId();
    }

    @Override
    @Transactional
    public Optional<ManagerDetails> updateById(Long id, ManagerRequest managerRequest) {
        if (!managerRepository.existsById(id))
            throw new EntityNotFoundException(id, Manager.class);

        managerRepository.save(managerMapper.toEntity(id, managerRequest));
        return findById(id);
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
