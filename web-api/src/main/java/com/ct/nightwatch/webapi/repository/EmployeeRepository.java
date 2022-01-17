package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    @NonNull
    @EntityGraph("EmployeeListItem")
    List<Employee> findAll();

    @EntityGraph("EmployeeDetails")
    Optional<Employee> findDetailsById(Long id);

    @EntityGraph("EmployeeListItem")
    List<Employee> findByDepartmentManager(Manager manager);

}
