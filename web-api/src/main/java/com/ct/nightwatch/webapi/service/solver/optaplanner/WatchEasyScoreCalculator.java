package com.ct.nightwatch.webapi.service.solver.optaplanner;

import com.ct.nightwatch.webapi.service.solver.optaplanner.model.Employee;
import com.ct.nightwatch.webapi.service.solver.optaplanner.model.Watch;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class WatchEasyScoreCalculator implements EasyScoreCalculator<WatchSolution, HardSoftScore> {

    @Override
    public HardSoftScore calculateScore(WatchSolution watchSolution) {
        List<Watch> watches = watchSolution.getWatches();
        List<Employee> employees = watchSolution.getEmployees();

        int hardScore = 0;
        int softScore = 0;

        for (Watch watch : watches) {

            Employee watchEmployee = watch.getEmployee();
            if (Objects.nonNull(watchEmployee)) {
                List<LocalDate> offDays = watchEmployee.getOffDays();
                if (Objects.nonNull(offDays)) {
                    // If the watch is on off day
                    if (offDays.contains(watch.getDate())) {
                        --hardScore;
                    }
                }

                List<LocalDate> preferredDays = watchEmployee.getPreferredDays();
                if (Objects.nonNull(preferredDays)) {
                    // If the watch is at a preferred day
                    if (preferredDays.contains(watch.getDate())) {
                        ++softScore;
                    }
                }
            }

            // For each unique pair of watches
            for (Watch watch2 : watches) {
                if (watch.getId().compareTo(watch2.getId()) < 0) {
                    // If there is no day between watches of an employee
                    if (watch.getEmployee() != null && watch2.getEmployee() != null
                            && watch.getEmployee() == watch2.getEmployee()
                            && Math.abs(ChronoUnit.DAYS.between(watch.getDate(), watch2.getDate())) < 2) {
                        --hardScore;
                    }

                }
            }

        }

        for (Employee employee : employees) {
            int dayCount = 0;
            for (Watch watch : watches) {
                Employee watchEmployee = watch.getEmployee();
                if (Objects.nonNull(watchEmployee)) {
                    if (watchEmployee == employee) {
                        dayCount++;
                    }

                    List<LocalDate> preferredDays =employee.getPreferredDays();
                    if (Objects.nonNull(preferredDays)) {
                        // If the preferred day is taken by lower level employee
                        if (preferredDays.contains(watch.getDate()) && watchEmployee.getLevel() < employee.getLevel()) {
                            --softScore;
                        }
                    }
                }
            }

            // If there are fewer watches than employee's mandatory day
            if (dayCount < employee.getMandatoryDay()) {
                --hardScore;
            }
        }

        return HardSoftScore.of(hardScore, softScore);
    }

}
