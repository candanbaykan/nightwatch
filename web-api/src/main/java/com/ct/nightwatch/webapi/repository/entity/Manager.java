package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "manager", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "ManagerDetails",
                attributeNodes = {
                        @NamedAttributeNode(Manager.Fields.departments),
                        @NamedAttributeNode(Manager.Fields.user)
                }
        )
})
public class Manager {

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

    @OneToMany(mappedBy = Department.Fields.manager, fetch = FetchType.LAZY)
    private List<Department> departments;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Manager() {
    }
}
