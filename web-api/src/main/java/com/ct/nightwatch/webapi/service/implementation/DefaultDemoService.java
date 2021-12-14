package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.DemoRepository;
import com.ct.nightwatch.webapi.repository.entity.Demo;
import com.ct.nightwatch.webapi.service.DemoService;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import com.ct.nightwatch.webapi.service.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultDemoService implements DemoService {

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    public DefaultDemoService(DemoRepository demoRepository, DemoMapper demoMapper) {
        this.demoRepository = demoRepository;
        this.demoMapper = demoMapper;
    }

    @Override
    public List<DemoSummary> findAll() {
        return demoRepository.findAll().stream()
                .map(demoMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DemoSummary> findById(Long id) {
        return demoRepository.findById(id)
                .map(demoMapper::toSummary);
    }

    @Override
    public Long save(DemoRequest demoRequest) {
        Demo demo = demoMapper.toEntity(demoRequest);
        return demoRepository.save(demo).getId();
    }


}