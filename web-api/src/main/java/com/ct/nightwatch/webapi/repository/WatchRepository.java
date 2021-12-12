package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

    @EntityGraph("WatchDetails")
    Optional<Watch> findDetailsById(Long id);

}
