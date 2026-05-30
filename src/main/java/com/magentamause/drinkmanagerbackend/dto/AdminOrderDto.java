package com.magentamause.drinkmanagerbackend.dto;

import com.magentamause.drinkmanagerbackend.entity.OrderStatus;
import java.time.Instant;
import java.util.List;

/** Full order view for the admin board, including garden location and timestamps. */
public record AdminOrderDto(
        Long id,
        String publicId,
        Long queueNumber,
        String guestName,
        double locationX,
        double locationY,
        String note,
        OrderStatus status,
        Integer position,
        Instant createdAt,
        Instant doneAt,
        List<OrderItemDto> items) {
}
