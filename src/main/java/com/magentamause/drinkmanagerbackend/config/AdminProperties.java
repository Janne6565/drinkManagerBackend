package com.magentamause.drinkmanagerbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Single admin credential, configured via app.admin.* (override with env in production). */
@ConfigurationProperties(prefix = "app.admin")
public record AdminProperties(String username, String password) {
}
