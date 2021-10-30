package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {
}
