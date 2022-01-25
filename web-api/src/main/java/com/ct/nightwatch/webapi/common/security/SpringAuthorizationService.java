package com.ct.nightwatch.webapi.common.security;

import com.ct.nightwatch.webapi.repository.*;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service("authService")
public class SpringAuthorizationService implements AuthorizationService {

    private final HttpServletRequest request;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final OffDayRepository offDayRepository;
    private final PreferredDayRepository preferredDayRepository;
    private final WatchRepository watchRepository;

    public SpringAuthorizationService(
            HttpServletRequest request,
            UserRepository userRepository,
            ManagerRepository managerRepository,
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            OffDayRepository offDayRepository,
            PreferredDayRepository preferredDayRepository,
            WatchRepository watchRepository
    ) {
        this.request = request;
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.offDayRepository = offDayRepository;
        this.preferredDayRepository = preferredDayRepository;
        this.watchRepository = watchRepository;
    }

    @Override
    public boolean isAdmin() {
        return request.isUserInRole("ROLE_ADMIN");
    }

    @Override
    public boolean isManager() {
        return request.isUserInRole("ROLE_MANAGER");
    }

    @Override
    public boolean isEmployee() {
        return request.isUserInRole("ROLE_EMPLOYEE");
    }

    @Override
    public boolean isEqualUser(Long userId) {
        return userRepository.existsByIdAndUsername(userId, getUsername());
    }

    @Override
    public boolean isEqualUser(String userId) {
        try {
            return isEqualUser(Long.parseLong(userId));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public boolean isEqualManager(Long managerId) {
        return isManager() && managerRepository.existsByIdAndUserUsername(managerId, getUsername());
    }

    @Override
    public boolean isEqualManager(String managerId) {
        try {
            return isEqualManager(Long.parseLong(managerId));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public boolean isEqualEmployee(Long employeeId) {
        return isEmployee() && employeeRepository.existsByIdAndUserUsername(employeeId, getUsername());
    }

    @Override
    public boolean isEqualEmployee(String employeeId) {
        try {
            return isEqualEmployee(Long.parseLong(employeeId));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public boolean isDepartmentManager(Long departmentId) {
        return isManager() && departmentRepository.existsByIdAndManagerUserUsername(departmentId, getUsername());
    }

    @Override
    public boolean isDepartmentEmployee(Long departmentId) {
        return isEmployee() && employeeRepository.existsByDepartmentIdAndUserUsername(departmentId, getUsername());
    }

    @Override
    public boolean isUserManager(Long userId) {
        return isManager() && employeeRepository.existsByUserIdAndDepartmentManagerUserUsername(userId, getUsername());
    }

    @Override
    public boolean isUserManager(String userId) {
        try {
            return isUserManager(Long.parseLong(userId));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public boolean isEmployeeManager(Long employeeId) {
        return isManager() && employeeRepository.existsByIdAndDepartmentManagerUserUsername(employeeId, getUsername());
    }

    @Override
    public boolean isEmployeeManager(String employeeId) {
        try {
            return isEmployeeManager(Long.parseLong(employeeId));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public boolean canViewOffDay(Long offDayId) {
        String username = getUsername();
        return isAdmin() ||
                (isManager() && offDayRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(offDayId, username)) ||
                (isEmployee() && offDayRepository.existsByIdAndEmployeeUserUsername(offDayId, username));
    }

    @Override
    public boolean canModifyOffDay(Long offDayId) {
        String username = getUsername();
        return isAdmin() ||
                (isManager() && offDayRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(offDayId, username));
    }

    @Override
    public boolean canModifyOffDay(Long offDayId, OffDayRequest offDayRequest) {
        String username = getUsername();
        return isAdmin() ||
                (isManager() &&
                        offDayRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(offDayId, username) &&
                        Objects.nonNull(offDayRequest) && Objects.nonNull(offDayRequest.getEmployeeId()) &&
                        isEmployeeManager(offDayRequest.getEmployeeId()));
    }

    @Override
    public boolean canViewPreferredDay(Long preferredDayId) {
        return isAdmin() ||
                (isManager() &&
                        preferredDayRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(preferredDayId, getUsername())) ||
                (isEmployee() &&
                        preferredDayRepository.existsByIdAndEmployeeUserUsername(preferredDayId, getUsername()));
    }

    @Override
    public boolean canModifyPreferredDay(Long preferredDayId) {
        return isEmployee() &&
                preferredDayRepository.existsByIdAndEmployeeUserUsername(preferredDayId, getUsername());
    }

    @Override
    public boolean canViewWatch(Long watchId) {
        return isAdmin() ||
                (isManager() &&
                        watchRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(watchId, getUsername())) ||
                (isEmployee() &&
                        watchRepository.existsByIdAndEmployeeUserUsername(watchId, getUsername()));
    }

    @Override
    public boolean canModifyWatch(Long watchId) {
        return isAdmin() ||
                (isManager() &&
                        watchRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(watchId, getUsername()));
    }

    @Override
    public boolean canModifyWatch(Long watchId, WatchRequest watchRequest) {
        return isAdmin() ||
                (isManager() &&
                        watchRepository.existsByIdAndEmployeeDepartmentManagerUserUsername(watchId, getUsername()) &&
                        Objects.nonNull(watchRequest) && Objects.nonNull(watchRequest.getEmployeeId()) &&
                        isEmployeeManager(watchRequest.getEmployeeId()));
    }

    private String getUsername() {
        return request.getUserPrincipal().getName();
    }

}
