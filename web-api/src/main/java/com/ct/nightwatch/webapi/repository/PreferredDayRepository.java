package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferredDayRepository extends JpaRepository<PreferredDay, Long> {
}
