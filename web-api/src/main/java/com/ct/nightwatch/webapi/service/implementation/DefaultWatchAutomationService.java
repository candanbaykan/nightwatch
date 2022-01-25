package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.DepartmentRepository;
import com.ct.nightwatch.webapi.repository.WatchRepository;
import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.WatchAutomationService;
import com.ct.nightwatch.webapi.service.solver.WatchSolver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class DefaultWatchAutomationService implements WatchAutomationService {

    private final DepartmentRepository departmentRepository;
    private final WatchRepository watchRepository;
    private final WatchSolver watchSolver;

    public DefaultWatchAutomationService(
            DepartmentRepository departmentRepository,
            WatchRepository watchRepository,
            WatchSolver watchSolver
    ) {
        this.departmentRepository = departmentRepository;
        this.watchRepository = watchRepository;
        this.watchSolver = watchSolver;
    }

    @Override
    @Scheduled(cron = "0 0 0 27 * *")
    @Transactional
    public void run() {
        List<Department> departments = departmentRepository.findAll();
        List<Watch> watches = watchSolver.solve(departments);
        watchRepository.saveAll(watches);
    }

    @Override
    @Transactional
    @PreAuthorize("@authService.isAdmin()")
    public void demo() {
        LocalDate beginDate = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate endDate = beginDate.with(TemporalAdjusters.lastDayOfMonth());
        watchRepository.deleteByDateBetween(beginDate.minusDays(1), endDate.plusDays(1));
        run();
    }
}
