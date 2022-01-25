package com.ct.nightwatch.webapi.common.security;

import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;

public interface AuthorizationService {
    boolean isAdmin();

    boolean isManager();

    boolean isEmployee();

    boolean isEqualUser(Long userId);

    boolean isEqualUser(String userId);

    boolean isEqualManager(Long managerId);

    boolean isEqualManager(String managerId);

    boolean isEqualEmployee(Long employeeId);

    boolean isEqualEmployee(String employeeId);

    boolean isDepartmentManager(Long departmentId);

    boolean isDepartmentEmployee(Long departmentId);

    boolean isUserManager(Long userId);

    boolean isUserManager(String userId);

    boolean isEmployeeManager(Long employeeId);

    boolean isEmployeeManager(String employeeId);

    boolean canViewOffDay(Long offDayId);

    boolean canModifyOffDay(Long offDayId);

    boolean canModifyOffDay(Long offDayId, OffDayRequest offDayRequest);

    boolean canViewPreferredDay(Long preferredDayId);

    boolean canModifyPreferredDay(Long preferredDayId);

    boolean canViewWatch(Long watchId);

    boolean canModifyWatch(Long watchId);

    boolean canModifyWatch(Long watchId, WatchRequest watchRequest);

}
