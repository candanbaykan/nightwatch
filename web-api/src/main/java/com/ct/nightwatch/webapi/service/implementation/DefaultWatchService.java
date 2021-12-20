package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.WatchRepository;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.WatchService;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.WatchMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultWatchService implements WatchService {

    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;

    public DefaultWatchService(WatchRepository watchRepository, WatchMapper watchMapper) {
        this.watchRepository = watchRepository;
        this.watchMapper = watchMapper;
    }

    @Override
    public List<WatchSummary> findAll() {
        return watchRepository.findAll().stream()
                .map(watchMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public WatchDetails findById(Long id) {
        return watchRepository.findDetailsById(id)
                .map(watchMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, Watch.class));
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
