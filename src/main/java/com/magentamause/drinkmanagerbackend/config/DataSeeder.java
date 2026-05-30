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
                new Drink("Bier", "Beer", "Kühles Pils vom Fass", "Cold draft pilsner", true),
                new Drink("Radler", "Shandy", "Bier mit Zitronenlimonade", "Beer with lemon soda", true),
                new Drink("Cola", "Cola", "Eiskalte Cola", "Ice-cold cola", true),
                new Drink("Fanta", "Fanta", "Orangenlimonade", "Orange soda", true),
                new Drink("Apfelschorle", "Apple Spritzer", "Apfelsaft mit Sprudel",
                        "Apple juice with sparkling water", true),
                new Drink("Wasser", "Water", "Still oder mit Kohlensäure", "Still or sparkling", true),
                new Drink("Sekt", "Sparkling Wine", "Für den Geburtstagstoast", "For the birthday toast", true),
                new Drink("Aperol Spritz", "Aperol Spritz", "Aperol, Prosecco, Soda", "Aperol, prosecco, soda", true),
                new Drink("Gin Tonic", "Gin & Tonic", "Gin mit Tonic Water", "Gin with tonic water", true),
                new Drink("Kaffee", "Coffee", "Frisch gebrüht", "Freshly brewed", true));
        drinkRepository.saveAll(drinks);
        log.info("Seeded {} drinks", drinks.size());
    }
}
