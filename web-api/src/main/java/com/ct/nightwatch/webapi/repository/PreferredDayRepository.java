package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferredDayRepository extends JpaRepository<PreferredDay, Long>, JpaSpecificationExecutor<PreferredDay> {

    @EntityGraph("PreferredDayDetails")
    Optional<PreferredDay> findDetailsById(Long id);

    @EntityGraph("PreferredDayDetails")
    List<PreferredDay> findByEmployeeDepartment(Department department);

    boolean existsByIdAndEmployeeDepartmentManagerUserUsername(Long id, String managerUsername);

    boolean existsByIdAndEmployeeUserUsername(Long id, String employeeUsername);

}
