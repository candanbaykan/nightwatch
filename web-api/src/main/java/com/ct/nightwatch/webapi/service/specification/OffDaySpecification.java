package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OffDaySpecification implements Specification<OffDay> {

    private final Map<String, String> parameters;

    public OffDaySpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<OffDay> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> joinEmployee = (Join<Object, Object>) root.fetch(OffDay.Fields.employee, JoinType.LEFT);

        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(OffDay.Fields.employee))
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
