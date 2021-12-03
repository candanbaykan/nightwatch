package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph("EmployeeDetails")
    Optional<Employee> findDetailsById(Long id);

}
