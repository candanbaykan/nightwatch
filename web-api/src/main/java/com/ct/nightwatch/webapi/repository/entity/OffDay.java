package com.ct.nightwatch.webapi.repository.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "off_day", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "OffDayDetails",
                attributeNodes = {
                        @NamedAttributeNode(OffDay.Fields.employee)
                }
        )
})
public class OffDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "begin")
    private Date begin;

    @NotNull
    @Column(name = "end")
    private Date end;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public OffDay() {
    }
}
