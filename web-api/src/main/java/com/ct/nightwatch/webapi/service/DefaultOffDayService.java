package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.OffDayRepository;
import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.OffDayMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<OffDaySummary> findAll() {
        return offDayRepository.findAll().stream()
                .map(offDayMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OffDayDetails> findById(Long id) {
        return offDayRepository.findDetailsById(id)
                .map(offDayMapper::toDetails);
    }

    @Override
    public Long save(OffDayRequest offDayRequest) {
        return offDayRepository.save(offDayMapper.toEntity(offDayRequest)).getId();
    }

    @Override
    public void updateById(Long id, OffDayRequest offDayRequest) {
        if (!offDayRepository.existsById(id))
            throw new EntityNotFoundException(id, OffDay.class);

        offDayRepository.save(offDayMapper.toEntity(id, offDayRequest));
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
