package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.repository.entity.Role;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserSpecification implements Specification<User> {

    private final Map<String, String> parameters;

    public UserSpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> joinRole = (Join<Object, Object>) root.fetch(User.Fields.role, JoinType.LEFT);
        Join<Object, Object> joinManager = (Join<Object, Object>) root.fetch(User.Fields.manager, JoinType.LEFT);
        Join<Object, Object> joinEmployee = (Join<Object, Object>) root.fetch(User.Fields.employee, JoinType.LEFT);

        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(User.Fields.role + "Id"))
                    .ifPresent(parameter -> {
                        predicates.add(criteriaBuilder.equal(joinRole.get(Role.Fields.id), parameter));
                    });

            Optional.ofNullable(parameters.get(User.Fields.manager + "Id"))
                    .ifPresent(parameter -> {
                        if (parameter.equals("null")) {
                            predicates.add(criteriaBuilder.isNull(joinManager));
                        } else {
                            predicates.add(criteriaBuilder.equal(joinManager.get(Manager.Fields.id), parameter));
                        }

                    });

            Optional.ofNullable(parameters.get(User.Fields.employee + "Id"))
                    .ifPresent(parameter -> {
                        if (parameter.equals("null")) {
                            predicates.add(criteriaBuilder.isNull(joinEmployee));
                        } else {
                            predicates.add(criteriaBuilder.equal(joinEmployee.get(Employee.Fields.id), parameter));
                        }

                    });

            SortingUtility.sort(root, query, criteriaBuilder, parameters);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }
}
