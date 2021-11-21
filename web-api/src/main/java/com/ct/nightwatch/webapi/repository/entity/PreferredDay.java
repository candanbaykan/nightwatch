package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "preferred_day", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "PreferredDayDetails",
                attributeNodes = {
                        @NamedAttributeNode(PreferredDay.Fields.employee)
                }
        )
})
public class PreferredDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public PreferredDay() {
    }
}
