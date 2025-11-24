package org.demo.restpattern.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SuperheroRequest(
        @NotBlank(message = "Name is required")
        String name,
        String fullName,
        String intelligence,
        String strength,
        String speed,
        String durability,
        String power,
        String combat,
        String gender,
        String race,
        String height,
        String weight,
        String eyeColor,
        String hairColor,
        String alignment,
        String publisher
) {}
