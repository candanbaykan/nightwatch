package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph("DepartmentDetails")
    Optional<Department> findDetailsById(Long id);

}
