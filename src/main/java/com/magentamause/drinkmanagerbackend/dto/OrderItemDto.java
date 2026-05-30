package com.magentamause.drinkmanagerbackend.dto;

public record OrderItemDto(Long drinkId, String drinkName, int quantity) {
}
