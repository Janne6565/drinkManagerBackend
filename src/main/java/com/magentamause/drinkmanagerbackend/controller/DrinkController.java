package com.magentamause.drinkmanagerbackend.controller;

import com.magentamause.drinkmanagerbackend.dto.DrinkDto;
import com.magentamause.drinkmanagerbackend.service.DrinkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drinks")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping
    public List<DrinkDto> getAvailableDrinks() {
        return drinkService.getAvailableDrinks();
    }
}
