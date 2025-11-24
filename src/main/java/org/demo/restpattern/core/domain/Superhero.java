package org.demo.restpattern.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Superhero {
    private long id;
    private String name;
    private String fullName;
    private String intelligence;
    private String strength;
    private String speed;
    private String durability;
    private String power;
    private String combat;
    private String gender;
    private String race;
    private String height;
    private String weight;
    private String eyeColor;
    private String hairColor;
    private String alignment;
    private String publisher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
