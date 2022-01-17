package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.WatchRepository;
import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.WatchService;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.DepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.WatchMapper;
import com.ct.nightwatch.webapi.service.specification.WatchSpecification;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultWatchService implements WatchService {

    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;
    private final DepartmentMapper departmentMapper;

    public DefaultWatchService(WatchRepository watchRepository, WatchMapper watchMapper, DepartmentMapper departmentMapper) {
        this.watchRepository = watchRepository;
        this.watchMapper = watchMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<WatchDetails> findAll(Map<String, String> parameters) {
        WatchSpecification specification = new WatchSpecification(parameters);
        return watchRepository.findAll(specification).stream()
                .map(watchMapper::toDetails)
                .collect(Collectors.toList());
    }

    @Override
    public WatchDetails findById(Long id) {
        return watchRepository.findDetailsById(id)
                .map(watchMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, Watch.class));
    }

    @Override
    public List<WatchDetails> findByDepartmentId(Long departmentId) {
        Department department = departmentMapper.toEntity(departmentId);
        return watchRepository.findByEmployeeDepartment(department).stream()
                .map(watchMapper::toDetails)
                .toList();
    }

    @Override
    public Long save(WatchRequest watchRequest) {
        Watch watch = watchMapper.toEntity(watchRequest);
        return watchRepository.save(watch).getId();
    }

    @Override
    public void updateById(Long id, WatchRequest watchRequest) {
        if (!watchRepository.existsById(id))
            throw new EntityNotFoundException(id, Watch.class);

        Watch watch = watchMapper.toEntity(id, watchRequest);
        watchRepository.save(watch);
    }

    @Override
    public void deleteById(Long id) {
        try {
            watchRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, Watch.class);
        }
    }

}
