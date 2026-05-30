package com.magentamause.drinkmanagerbackend.dto;

import com.magentamause.drinkmanagerbackend.entity.OrderStatus;
import java.time.Instant;
import java.util.List;

/** What a guest sees about their own order, including the live queue position. */
public record OrderResponse(
        String publicId,
        Long queueNumber,
        String guestName,
        OrderStatus status,
        Integer position,
        Instant createdAt,
        List<OrderItemDto> items) {
}
