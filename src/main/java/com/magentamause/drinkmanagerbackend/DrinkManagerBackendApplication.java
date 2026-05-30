package com.magentamause.drinkmanagerbackend;

import com.magentamause.drinkmanagerbackend.config.AdminProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AdminProperties.class)
public class DrinkManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrinkManagerBackendApplication.class, args);
    }

}
