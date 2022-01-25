package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeSpecification implements Specification<Employee> {

    private final Map<String, String> parameters;

    public EmployeeSpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> joinDepartment = (Join<Object, Object>) root.fetch(Employee.Fields.department, JoinType.LEFT);
        Join<Object, Object> joinRank = (Join<Object, Object>) root.fetch(Employee.Fields.rank, JoinType.LEFT);

        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(Employee.Fields.user + "Id"))
                    .ifPresent(parameter -> {
                        Join<Object, Object> joinUser = (Join<Object, Object>) root.fetch(Employee.Fields.user, JoinType.LEFT);
                        predicates.add(criteriaBuilder.equal(joinUser.get(User.Fields.id), parameter));
                    });

            SortingUtility.sort(root, query, criteriaBuilder, parameters);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

}
