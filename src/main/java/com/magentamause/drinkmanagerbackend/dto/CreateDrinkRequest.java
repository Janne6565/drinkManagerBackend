package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDrinkRequest(
        @NotBlank String name,
        String description,
        Boolean available) {
}
