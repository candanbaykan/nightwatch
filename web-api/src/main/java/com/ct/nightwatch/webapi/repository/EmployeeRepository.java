package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @EntityGraph("EmployeeDetails")
    Optional<Employee> findDetailsById(Long id);

    @EntityGraph("EmployeeListItem")
    List<Employee> findByDepartmentManager(Manager manager);

    boolean existsByIdAndUserUsername(Long id, String username);

    boolean existsByIdAndDepartmentManagerUserUsername(Long id, String managerUsername);

    boolean existsByDepartmentIdAndUserUsername(Long departmentId, String username);

    boolean existsByUserIdAndDepartmentManagerUserUsername(Long userId, String managerUsername);

}
