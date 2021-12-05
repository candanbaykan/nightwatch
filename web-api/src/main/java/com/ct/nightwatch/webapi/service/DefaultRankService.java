package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.RankRepository;
import com.ct.nightwatch.webapi.repository.entity.Rank;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.RankMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultRankService implements RankService {

    private final RankRepository rankRepository;
    private final RankMapper rankMapper;

    public DefaultRankService(RankRepository rankRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
        this.rankMapper = rankMapper;
    }

    @Override
    public List<RankSummary> findAll() {
        return rankRepository.findAll().stream()
                .map(rankMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RankSummary> findById(Long id) {
        return rankRepository.findById(id)
                .map(rankMapper::toSummary);
    }

    @Override
    public Long save(RankRequest rankRequest) {
        return rankRepository.save(rankMapper.toEntity(rankRequest)).getId();
    }

    @Override
    public void updateById(Long id, RankRequest rankRequest) {
        if (!rankRepository.existsById(id))
            throw new EntityNotFoundException(id, Rank.class);

        rankRepository.save(rankMapper.toEntity(id, rankRequest));
    }

    @Override
    public void deleteById(Long id) {
        try {
            rankRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, Rank.class);
        }
    }

}
