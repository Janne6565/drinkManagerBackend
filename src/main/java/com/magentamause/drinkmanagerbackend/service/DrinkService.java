package com.magentamause.drinkmanagerbackend.service;

import com.magentamause.drinkmanagerbackend.dto.CreateDrinkRequest;
import com.magentamause.drinkmanagerbackend.dto.DrinkDto;
import com.magentamause.drinkmanagerbackend.dto.UpdateDrinkRequest;
import com.magentamause.drinkmanagerbackend.entity.Drink;
import com.magentamause.drinkmanagerbackend.exception.NotFoundException;
import com.magentamause.drinkmanagerbackend.mapper.DrinkMapper;
import com.magentamause.drinkmanagerbackend.repository.DrinkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    @Transactional(readOnly = true)
    public List<DrinkDto> getAvailableDrinks() {
        return drinkRepository.findAllByAvailableTrueOrderByNameAsc().stream()
                .map(drinkMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DrinkDto> getAllDrinks() {
        return drinkRepository.findAllByOrderByNameAsc().stream()
                .map(drinkMapper::toDto)
                .toList();
    }

    @Transactional
    public DrinkDto create(CreateDrinkRequest request) {
        boolean available = request.available() == null || request.available();
        Drink drink = new Drink(
                request.name().trim(),
                trimToNull(request.nameEn()),
                request.description(),
                trimToNull(request.descriptionEn()),
                available);
        return drinkMapper.toDto(drinkRepository.save(drink));
    }

    @Transactional
    public DrinkDto update(Long id, UpdateDrinkRequest request) {
        Drink drink = getEntity(id);
        drink.setName(request.name().trim());
        drink.setNameEn(trimToNull(request.nameEn()));
        drink.setDescription(request.description());
        drink.setDescriptionEn(trimToNull(request.descriptionEn()));
        drink.setAvailable(request.available());
        return drinkMapper.toDto(drinkRepository.save(drink));
    }

    @Transactional
    public void delete(Long id) {
        Drink drink = getEntity(id);
        drinkRepository.delete(drink);
    }

    @Transactional(readOnly = true)
    public Drink getEntity(Long id) {
        return drinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Drink not found: " + id));
    }

    private static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
