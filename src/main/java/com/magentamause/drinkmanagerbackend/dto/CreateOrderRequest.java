package com.magentamause.drinkmanagerbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderRequest(
        @NotBlank String guestName,
        @DecimalMin("0.0") @DecimalMax("1.0") double locationX,
        @DecimalMin("0.0") @DecimalMax("1.0") double locationY,
        String note,
        @NotEmpty @Valid List<OrderItemRequest> items) {
}
