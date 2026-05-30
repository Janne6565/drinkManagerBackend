package com.magentamause.drinkmanagerbackend.mapper;

import com.magentamause.drinkmanagerbackend.dto.DrinkDto;
import com.magentamause.drinkmanagerbackend.entity.Drink;
import org.springframework.stereotype.Component;

@Component
public class DrinkMapper {

    public DrinkDto toDto(Drink drink) {
        return new DrinkDto(drink.getId(), drink.getName(), drink.getDescription(), drink.isAvailable());
    }
}
