package org.demo.restpattern.dataprovider.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "superheroes")
public class SuperheroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "hair_color")
    private String hairColor;

    private String alignment;
    private String publisher;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
