package com.magentamause.drinkmanagerbackend.mapper;

import com.magentamause.drinkmanagerbackend.dto.AdminOrderDto;
import com.magentamause.drinkmanagerbackend.dto.OrderItemDto;
import com.magentamause.drinkmanagerbackend.dto.OrderResponse;
import com.magentamause.drinkmanagerbackend.entity.Order;
import com.magentamause.drinkmanagerbackend.entity.OrderItem;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order, Integer position) {
        return new OrderResponse(
                order.getPublicId(),
                order.getQueueNumber(),
                order.getGuestName(),
                order.getStatus(),
                position,
                order.getCreatedAt(),
                toItemDtos(order.getItems()));
    }

    public AdminOrderDto toAdminDto(Order order, Integer position) {
        return new AdminOrderDto(
                order.getId(),
                order.getPublicId(),
                order.getQueueNumber(),
                order.getGuestName(),
                order.getLocationX(),
                order.getLocationY(),
                order.getNote(),
                order.getStatus(),
                position,
                order.getCreatedAt(),
                order.getDoneAt(),
                toItemDtos(order.getItems()));
    }

    private List<OrderItemDto> toItemDtos(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemDto(item.getDrinkId(), item.getDrinkName(), item.getQuantity()))
                .toList();
    }
}
