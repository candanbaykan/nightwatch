package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffDayRepository extends JpaRepository<OffDay, Long> {

    @EntityGraph("OffDayDetails")
    Optional<OffDay> findDetailsById(Long id);

}
