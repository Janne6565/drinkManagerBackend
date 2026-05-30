package com.magentamause.drinkmanagerbackend.controller;

import com.magentamause.drinkmanagerbackend.dto.AdminOrderDto;
import com.magentamause.drinkmanagerbackend.entity.OrderStatus;
import com.magentamause.drinkmanagerbackend.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public List<AdminOrderDto> listOrders(@RequestParam(required = false) OrderStatus status) {
        return orderService.listOrders(status);
    }

    @PostMapping("/{id}/done")
    public AdminOrderDto markDone(@PathVariable Long id) {
        return orderService.markDone(id);
    }

    @PostMapping("/{id}/reopen")
    public AdminOrderDto reopen(@PathVariable Long id) {
        return orderService.reopen(id);
    }
}
