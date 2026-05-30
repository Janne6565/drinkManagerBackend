package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDrinkRequest(
        @NotBlank String name,
        String nameEn,
        String description,
        String descriptionEn,
        Boolean available) {
}
