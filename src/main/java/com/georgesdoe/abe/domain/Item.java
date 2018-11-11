package com.georgesdoe.abe.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String code;

    String description;

    Float baseFee;

    Integer capacity;

    Boolean active;
}
