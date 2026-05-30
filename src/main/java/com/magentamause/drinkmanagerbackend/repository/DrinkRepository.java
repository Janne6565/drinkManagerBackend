package com.magentamause.drinkmanagerbackend.repository;

import com.magentamause.drinkmanagerbackend.entity.Drink;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findAllByAvailableTrueOrderByNameAsc();

    List<Drink> findAllByOrderByNameAsc();
}
