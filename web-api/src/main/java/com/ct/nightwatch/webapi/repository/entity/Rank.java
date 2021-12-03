package com.ct.nightwatch.webapi.repository.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rank", schema = "public")
@Getter
@Setter
@FieldNameConstants
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "mandatory_day")
    private Integer mandatoryDay;

    @NotNull
    @Column(name = "level")
    private Integer level;

    public Rank() {
    }

}
