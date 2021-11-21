package com.ct.nightwatch.webapi.repository.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "department", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "DepartmentDetails",
                attributeNodes = {
                        @NamedAttributeNode(Department.Fields.manager),
                        @NamedAttributeNode(Department.Fields.employees)
                }
        )
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "daily_watch")
    private Integer dailyWatch;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department() {
    }
}
