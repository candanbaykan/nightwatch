package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToDepartment;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToEmployee;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToRank;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {
        DepartmentMapper.class, RankMapper.class, UserMapper.class,
        OffDayMapper.class, PreferredDayMapper.class, WatchMapper.class
})
public interface EmployeeMapper {

    EmployeeSummary toSummary(Employee employee);

    EmployeeDetails toDetails(Employee employee);

    @IdToEmployee
    Employee toEntity(Long id);

    @Mappings({
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.departmentId,
                    target = Employee.Fields.department,
                    qualifiedBy = IdToDepartment.class
            ),
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.rankId,
                    target = Employee.Fields.rank,
                    qualifiedBy = IdToRank.class
            ),
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.userId,
                    target = Employee.Fields.user,
                    qualifiedBy = IdToUser.class
            )
    })
    Employee toEntity(EmployeeRequest employeeRequest);

    @Mappings({
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.departmentId,
                    target = Employee.Fields.department,
                    qualifiedBy = IdToDepartment.class
            ),
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.rankId,
                    target = Employee.Fields.rank,
                    qualifiedBy = IdToRank.class
            ),
            @Mapping(
                    source = "employeeRequest." + EmployeeRequest.Fields.userId,
                    target = Employee.Fields.user,
                    qualifiedBy = IdToUser.class
            )
    })
    Employee toEntity(Long id, EmployeeRequest employeeRequest);
}
