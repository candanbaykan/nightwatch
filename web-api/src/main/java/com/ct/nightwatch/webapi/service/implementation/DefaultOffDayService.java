package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.OffDayRepository;
import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.OffDayService;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.OffDayMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultOffDayService implements OffDayService {

    private final OffDayRepository offDayRepository;
    private final OffDayMapper offDayMapper;

    public DefaultOffDayService(OffDayRepository offDayRepository, OffDayMapper offDayMapper) {
        this.offDayRepository = offDayRepository;
        this.offDayMapper = offDayMapper;
    }

    @Override
    public List<OffDayDetails> findAll() {
        return offDayRepository.findAll().stream()
                .map(offDayMapper::toDetails)
                .collect(Collectors.toList());
    }

    @Override
    public OffDayDetails findById(Long id) {
        return offDayRepository.findDetailsById(id)
                .map(offDayMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, OffDay.class));
    }

    @Override
    public Long save(OffDayRequest offDayRequest) {
        OffDay offDay = offDayMapper.toEntity(offDayRequest);
        return offDayRepository.save(offDay).getId();
    }

    @Override
    public void updateById(Long id, OffDayRequest offDayRequest) {
        if (!offDayRepository.existsById(id))
            throw new EntityNotFoundException(id, OffDay.class);

        OffDay offDay = offDayMapper.toEntity(id, offDayRequest);
        offDayRepository.save(offDay);
    }

    @Override
    public void deleteById(Long id) {
        try {
            offDayRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, OffDay.class);
        }
    }
}
