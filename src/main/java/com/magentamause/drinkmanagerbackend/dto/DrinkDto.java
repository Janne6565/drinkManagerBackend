package com.magentamause.drinkmanagerbackend.dto;

public record DrinkDto(
        Long id, String name, String nameEn, String description, String descriptionEn, boolean available) {
}
