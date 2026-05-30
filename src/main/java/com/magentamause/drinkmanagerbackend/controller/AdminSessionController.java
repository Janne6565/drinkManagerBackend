package com.magentamause.drinkmanagerbackend.controller;

import java.security.Principal;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Lets the frontend verify the entered admin credentials before showing the dashboard. */
@RestController
@RequestMapping("/api/admin")
public class AdminSessionController {

    @GetMapping("/me")
    public Map<String, String> me(Principal principal) {
        return Map.of("username", principal.getName());
    }
}
