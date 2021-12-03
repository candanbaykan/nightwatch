package com.ct.nightwatch.webapi.service.mapper.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToDepartment;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToRank;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToUser;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleDepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleRankMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleUserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {
                SimpleDepartmentMapper.class, SimpleRankMapper.class, SimpleUserMapper.class
        },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexEmployeeMapper {

    EmployeeDetails toDetails(Employee employee);

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
