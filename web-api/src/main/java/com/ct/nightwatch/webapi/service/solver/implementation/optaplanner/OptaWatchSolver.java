package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.solver.WatchSolver;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.mapper.OptaModelMapper;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.EmployeePlanningVariable;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.WatchPlanningEntity;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.WatchPlanningSolution;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
public class OptaWatchSolver implements WatchSolver {

    private final OptaModelMapper optaModelMapper;
    private final SolverManager<WatchPlanningSolution, UUID> solverManager;

    private static final Logger logger = LoggerFactory.getLogger(OptaWatchSolver.class);

    public OptaWatchSolver(OptaModelMapper optaModelMapper, SolverManager<WatchPlanningSolution, UUID> solverManager) {
        this.optaModelMapper = optaModelMapper;
        this.solverManager = solverManager;
    }

    @Override
    public List<Watch> solve(List<Department> departments) {
        List<WatchPlanningSolution> solutions = new ArrayList<>(departments.size());
        List<SolverJob<WatchPlanningSolution, UUID>> jobs = new ArrayList<>(departments.size());

        for (Department department : departments) {
            List<WatchPlanningEntity> watchPlanningEntities = new ArrayList<>();
            List<EmployeePlanningVariable> employeePlanningVariables = department.getEmployees().stream()
                    .map(optaModelMapper::toOptaModel)
                    .toList();

            LocalDate beginDate = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
            LocalDate endDate = beginDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);

            for (LocalDate currentDate = beginDate;
                 currentDate.isBefore(endDate);
                 currentDate = currentDate.plusDays(1)) {

                for (int i = 0; i < department.getDailyWatch(); ++i) {
                    WatchPlanningEntity watchPlanningEntity = new WatchPlanningEntity();
                    watchPlanningEntity.setDate(currentDate);
                    watchPlanningEntities.add(watchPlanningEntity);
                }
            }

            WatchPlanningSolution problem = new WatchPlanningSolution(watchPlanningEntities, employeePlanningVariables);
            jobs.add(solverManager.solve(UUID.randomUUID(), problem));
        }

        jobs.forEach(job -> {
            try {
                solutions.add(job.getFinalBestSolution());
            } catch (ExecutionException | InterruptedException e) {
                logger.error("An error occurred when getting a solution", e);
            }
        });

        return solutions.stream()
                .map(WatchPlanningSolution::getWatches)
                .flatMap(Collection::stream)
                .map(optaModelMapper::toEntity)
                .toList();
    }

}
