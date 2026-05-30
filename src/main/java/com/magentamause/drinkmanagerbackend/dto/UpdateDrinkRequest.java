package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateDrinkRequest(
        @NotBlank String name,
        String description,
        boolean available) {
}
