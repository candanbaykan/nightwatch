package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
