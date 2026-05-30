package com.magentamause.drinkmanagerbackend.controller;

import com.magentamause.drinkmanagerbackend.dto.CreateDrinkRequest;
import com.magentamause.drinkmanagerbackend.dto.DrinkDto;
import com.magentamause.drinkmanagerbackend.dto.UpdateDrinkRequest;
import com.magentamause.drinkmanagerbackend.service.DrinkService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/drinks")
@RequiredArgsConstructor
public class AdminDrinkController {

    private final DrinkService drinkService;

    @GetMapping
    public List<DrinkDto> listAll() {
        return drinkService.getAllDrinks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DrinkDto create(@Valid @RequestBody CreateDrinkRequest request) {
        return drinkService.create(request);
    }

    @PutMapping("/{id}")
    public DrinkDto update(@PathVariable Long id, @Valid @RequestBody UpdateDrinkRequest request) {
        return drinkService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        drinkService.delete(id);
    }
}
