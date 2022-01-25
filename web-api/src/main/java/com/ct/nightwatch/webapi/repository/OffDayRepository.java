package com.ct.nightwatch.webapi.repository;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.OffDay;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffDayRepository extends JpaRepository<OffDay, Long>, JpaSpecificationExecutor<OffDay> {

    @EntityGraph("OffDayDetails")
    Optional<OffDay> findDetailsById(Long id);

    @EntityGraph("OffDayDetails")
    List<OffDay> findByEmployeeDepartment(Department department);

    boolean existsByIdAndEmployeeDepartmentManagerUserUsername(Long id, String managerUsername);

    boolean existsByIdAndEmployeeUserUsername(Long id, String employeeUsername);

}
