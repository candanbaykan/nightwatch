package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
