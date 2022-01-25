package com.ct.nightwatch.webapi.service.specification;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.repository.entity.User;
import com.ct.nightwatch.webapi.service.exception.InvalidParameterException;
import com.ct.nightwatch.webapi.service.specification.utility.SortingUtility;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ManagerSpecification implements Specification<Manager> {

    private final Map<String, String> parameters;

    public ManagerSpecification(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        try {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(parameters.get(Manager.Fields.user + "Id"))
                    .ifPresent(parameter -> {
                        Join<Object, Object> joinUser = (Join<Object, Object>) root.fetch(Manager.Fields.user, JoinType.LEFT);
                        predicates.add(criteriaBuilder.equal(joinUser.get(User.Fields.id), parameter));
                    });

            SortingUtility.sort(root, query, criteriaBuilder, parameters);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }
}
