package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "UserDetails",
                attributeNodes = {
                        @NamedAttributeNode(User.Fields.role),
                }
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 152)
    @NotNull
    @Column(name = "username")
    private String username;

    @Size(max = 60)
    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = Manager.Fields.user, fetch = FetchType.LAZY, optional = false)
    private Manager manager;

    @OneToOne(mappedBy = Employee.Fields.user, fetch = FetchType.LAZY, optional = false)
    private Employee employee;

    public User() {
    }
}
