package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employee", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "EmployeeDetails",
                attributeNodes = {
                        @NamedAttributeNode(Employee.Fields.department),
                        @NamedAttributeNode(Employee.Fields.rank),
                        @NamedAttributeNode(Employee.Fields.user)
                }
        ),
        @NamedEntityGraph(
                name = "EmployeeListItem",
                attributeNodes = {
                        @NamedAttributeNode(Employee.Fields.department),
                        @NamedAttributeNode(Employee.Fields.rank)
                }
        )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 50)
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone")
    private String phone;

    @Size(max = 254)
    @NotNull
    @Column(name = "mail")
    private String mail;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<OffDay> offDays;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<PreferredDay> preferredDays;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Watch> watches;

    public Employee() {
    }
}
