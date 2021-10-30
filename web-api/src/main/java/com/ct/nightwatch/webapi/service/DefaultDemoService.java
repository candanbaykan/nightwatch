package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.DemoRepository;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import com.ct.nightwatch.webapi.service.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultDemoService implements DemoService {

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    @Override
    public List<DemoSummary> findAll() {
        return demoRepository.findAll().stream()
                .map(demoMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(DemoRequest demoRequest) {
        return demoRepository.save(demoMapper.toEntity(demoRequest)).getId();
    }


}
