package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role", schema = "public")
@Getter
@Setter
@FieldNameConstants
public class Role {

    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name")
    private String name;

    public Role() {
    }
}
