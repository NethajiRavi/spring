package com.own.jsonschemavalidator.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_schema")
@Getter @Setter
public class MasterSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "jsonb")
    private String sample;

    private String value;
}
