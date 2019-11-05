package com.md.serviceskeleton.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HelloDTO {

    @JsonIgnore
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String uuid;
}
