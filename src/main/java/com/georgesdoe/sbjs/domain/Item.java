package com.georgesdoe.sbjs.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    private Float baseFee;

    private Integer capacity;

    private Boolean active;
}
