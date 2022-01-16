package com.ct.nightwatch.webapi.service.solver;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Watch;

import java.util.List;

public interface WatchSolver {

    List<Watch> solve(List<Department> departments);

}
