package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DepartmentSpecification implements Specification<Department> {

    private final Map<String, String> parameters;

    public DepartmentSpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> joinManager = (Join<Object, Object>) root.fetch(Department.Fields.manager, JoinType.LEFT);

        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(Department.Fields.manager + "Id"))
                    .ifPresent(parameter -> {
                        predicates.add(criteriaBuilder.equal(joinManager.get(Manager.Fields.id), parameter));
                    });

            SortingUtility.sort(root, query, criteriaBuilder, parameters);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

}
