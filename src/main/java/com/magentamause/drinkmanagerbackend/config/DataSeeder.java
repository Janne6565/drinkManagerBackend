package com.magentamause.drinkmanagerbackend.config;

import com.magentamause.drinkmanagerbackend.entity.Drink;
import com.magentamause.drinkmanagerbackend.repository.DrinkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Seeds a starter drink list on first startup so the menu is never empty. */
@Component
@Slf4j
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final DrinkRepository drinkRepository;

    @Override
    public void run(String... args) {
        if (drinkRepository.count() > 0) {
            return;
        }
        List<Drink> drinks = List.of(
                new Drink("Bier", "Kühles Pils vom Fass", true),
                new Drink("Radler", "Bier mit Zitronenlimonade", true),
                new Drink("Cola", "Eiskalte Cola", true),
                new Drink("Fanta", "Orangenlimonade", true),
                new Drink("Apfelschorle", "Apfelsaft mit Sprudel", true),
                new Drink("Wasser", "Still oder mit Kohlensäure", true),
                new Drink("Sekt", "Für den Geburtstagstoast", true),
                new Drink("Aperol Spritz", "Aperol, Prosecco, Soda", true),
                new Drink("Gin Tonic", "Gin mit Tonic Water", true),
                new Drink("Kaffee", "Frisch gebrüht", true));
        drinkRepository.saveAll(drinks);
        log.info("Seeded {} drinks", drinks.size());
    }
}
