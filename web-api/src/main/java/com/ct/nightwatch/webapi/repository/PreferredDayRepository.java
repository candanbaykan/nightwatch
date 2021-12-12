package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferredDayRepository extends JpaRepository<PreferredDay, Long> {

    @EntityGraph("PreferredDayDetails")
    Optional<PreferredDay> findDetailsById(Long id);

}
