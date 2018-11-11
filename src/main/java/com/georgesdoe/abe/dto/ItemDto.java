package com.georgesdoe.abe.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ItemDto {

    @NotNull
    private String name;

    private String code;

    private String description;

    @Min(0)
    private Float baseFee = 0.0f;

    @Min(0)
    private Integer capacity = 0;

    private Boolean active = true;
}
