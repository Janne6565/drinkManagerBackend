package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateDrinkRequest(
        @NotBlank String name,
        String nameEn,
        String description,
        String descriptionEn,
        boolean available) {
}
