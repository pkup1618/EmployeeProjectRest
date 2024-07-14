package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;


@Getter
@NoArgsConstructor
@Table("employee")
public class Employee {

    @Id()
    @Column("id")
    @Setter
    private Long id;

    @Column("name")
    @Setter
    private String name;

    @Column("bossId")
    @Setter
    private Long bossId;

    @PersistenceCreator
    public Employee(Long id, String name, Long bossId) {
        this.id = id;
        this.name = name;
        this.bossId = bossId;
    }
}