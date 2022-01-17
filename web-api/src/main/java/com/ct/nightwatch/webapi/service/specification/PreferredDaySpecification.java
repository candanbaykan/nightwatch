package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PreferredDaySpecification implements Specification<PreferredDay> {

    private final Map<String, String> parameters;

    public PreferredDaySpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<PreferredDay> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> joinEmployee = (Join<Object, Object>) root.fetch(PreferredDay.Fields.employee, JoinType.LEFT);

        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(PreferredDay.Fields.employee))
                    .ifPresent(parameter -> {
                        predicates.add(criteriaBuilder.equal(joinEmployee.get(Employee.Fields.id), parameter));
                    });

            SortingUtility.sort(root, query, criteriaBuilder, parameters);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

}
