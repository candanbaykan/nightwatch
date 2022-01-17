package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long>, JpaSpecificationExecutor<Watch> {

    @EntityGraph("WatchDetails")
    Optional<Watch> findDetailsById(Long id);

    @EntityGraph("WatchDetails")
    List<Watch> findByEmployeeDepartment(Department department);

}
