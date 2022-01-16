package com.ct.nightwatch.webapi.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "watch", schema = "public")
@Getter
@Setter
@FieldNameConstants
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "WatchDetails",
                attributeNodes = {
                        @NamedAttributeNode(Watch.Fields.employee)
                }
        )
})
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Watch() {
    }
}
