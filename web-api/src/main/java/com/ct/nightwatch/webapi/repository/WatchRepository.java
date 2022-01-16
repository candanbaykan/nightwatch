package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

    @Override
    @NonNull
    @EntityGraph("WatchDetails")
    List<Watch> findAll();

    @EntityGraph("WatchDetails")
    Optional<Watch> findDetailsById(Long id);

}
