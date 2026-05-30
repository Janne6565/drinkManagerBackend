package com.magentamause.drinkmanagerbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drinks")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    /** English name; falls back to {@link #name} on the client when null. */
    @Column(name = "name_en")
    private String nameEn;

    @Column(length = 1000)
    private String description;

    /** English description; falls back to {@link #description} on the client when null. */
    @Column(name = "description_en", length = 1000)
    private String descriptionEn;

    @Column(nullable = false)
    private boolean available = true;

    public Drink(String name, String description, boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }

    public Drink(String name, String nameEn, String description, String descriptionEn, boolean available) {
        this.name = name;
        this.nameEn = nameEn;
        this.description = description;
        this.descriptionEn = descriptionEn;
        this.available = available;
    }
}
