package com.ct.nightwatch.webapi.service.specification.utility;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class SortingUtility {

    public static void sort(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Map<String, String> parameters) {
        String sort = parameters.get("sort");
        Optional.ofNullable(sort)
                .ifPresent(s -> {
                    List<String> split = new ArrayList<>(Arrays.asList(s.split(",")));
                    if (split.size() == 1) {
                        split.add("asc");
                    }
                    query.orderBy(
                            switch (split.get(1)) {
                                case "desc" -> criteriaBuilder.desc(root.get(split.get(0)));
                                default -> criteriaBuilder.asc(root.get(split.get(0)));
                            }
                    );
                });
    }

}
