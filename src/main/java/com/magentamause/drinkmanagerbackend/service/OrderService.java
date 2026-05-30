package com.magentamause.drinkmanagerbackend.service;

import com.magentamause.drinkmanagerbackend.dto.AdminOrderDto;
import com.magentamause.drinkmanagerbackend.dto.CreateOrderRequest;
import com.magentamause.drinkmanagerbackend.dto.OrderItemRequest;
import com.magentamause.drinkmanagerbackend.dto.OrderResponse;
import com.magentamause.drinkmanagerbackend.entity.Drink;
import com.magentamause.drinkmanagerbackend.entity.Order;
import com.magentamause.drinkmanagerbackend.entity.OrderItem;
import com.magentamause.drinkmanagerbackend.entity.OrderStatus;
import com.magentamause.drinkmanagerbackend.exception.NotFoundException;
import com.magentamause.drinkmanagerbackend.mapper.OrderMapper;
import com.magentamause.drinkmanagerbackend.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DrinkService drinkService;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        String publicId = UUID.randomUUID().toString();
        long queueNumber = orderRepository.count() + 1;

        Order order = new Order(
                publicId,
                queueNumber,
                request.guestName().trim(),
                request.locationX(),
                request.locationY(),
                normalizeNote(request.note()));

        for (OrderItemRequest itemRequest : request.items()) {
            Drink drink = drinkService.getEntity(itemRequest.drinkId());
            if (!drink.isAvailable()) {
                throw new IllegalArgumentException("Drink is not available: " + drink.getName());
            }
            order.addItem(new OrderItem(
                    drink.getId(), drink.getName(), itemRequest.quantity(), itemRequest.hasGlass()));
        }

        Order saved = orderRepository.save(order);
        return orderMapper.toResponse(saved, computePosition(saved));
    }

    @Transactional(readOnly = true)
    public OrderResponse getByPublicId(String publicId) {
        Order order = orderRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Order not found: " + publicId));
        return orderMapper.toResponse(order, computePosition(order));
    }

    @Transactional(readOnly = true)
    public List<AdminOrderDto> listOrders(OrderStatus statusFilter) {
        List<Order> orders = statusFilter == null
                ? orderRepository.findAllByOrderByCreatedAtDesc()
                : orderRepository.findAllByStatusOrderByCreatedAtAsc(statusFilter);
        return orders.stream()
                .map(order -> orderMapper.toAdminDto(order, computePosition(order)))
                .toList();
    }

    @Transactional
    public AdminOrderDto markDone(Long id) {
        Order order = getEntity(id);
        order.markDone();
        Order saved = orderRepository.save(order);
        return orderMapper.toAdminDto(saved, computePosition(saved));
    }

    @Transactional
    public AdminOrderDto reopen(Long id) {
        Order order = getEntity(id);
        order.reopen();
        Order saved = orderRepository.save(order);
        return orderMapper.toAdminDto(saved, computePosition(saved));
    }

    /**
     * Live queue position: how many open orders are ahead of (and including) this one.
     * Returns {@code null} for orders that are already done.
     */
    private Integer computePosition(Order order) {
        if (order.getStatus() != OrderStatus.OPEN) {
            return null;
        }
        return (int) orderRepository.countByStatusAndCreatedAtLessThanEqual(OrderStatus.OPEN, order.getCreatedAt());
    }

    private Order getEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found: " + id));
    }

    private String normalizeNote(String note) {
        if (note == null) {
            return null;
        }
        String trimmed = note.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
