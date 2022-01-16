package com.ct.nightwatch.webapi.repository.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private LocalDate begin;

    @NotNull
    @Column(name = "end")
    private LocalDate end;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public OffDay() {
    }
}
