package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim;
import com.ct.nightwatch.webapi.repository.RankRepository;
import com.ct.nightwatch.webapi.repository.entity.Rank;
import com.ct.nightwatch.webapi.service.RankService;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.RankMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public RankSummary findById(Long id) {
        return rankRepository.findById(id)
                .map(rankMapper::toSummary)
                .orElseThrow(() -> new EntityNotFoundException(id, Rank.class));
    }

    @Override
    public Long save(@Trim RankRequest rankRequest) {
        Rank rank = rankMapper.toEntity(rankRequest);
        return rankRepository.save(rank).getId();
    }

    @Override
    public void updateById(Long id, @Trim RankRequest rankRequest) {
        if (!rankRepository.existsById(id))
            throw new EntityNotFoundException(id, Rank.class);

        Rank rank = rankMapper.toEntity(id, rankRequest);
        rankRepository.save(rank);
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
