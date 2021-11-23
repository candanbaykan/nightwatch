package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.OffDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffDayRepository extends JpaRepository<OffDay, Long> {
}
