package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
        @NotNull Long drinkId,
        @Min(1) int quantity,
        boolean hasGlass) {
}
