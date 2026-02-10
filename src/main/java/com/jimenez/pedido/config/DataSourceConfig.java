package com.jimenez.pedido.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String dbUrl = environment.getProperty("spring.datasource.url");
        
        System.out.println("DEBUG: Original DATABASE_URL: " + dbUrl);
        
        if (dbUrl == null || dbUrl.isEmpty() || dbUrl.contains("$")) {
            System.out.println("DEBUG: Variable no resuelto, usando fallback");
            // Fallback for local development
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", "jdbc:postgresql://localhost:5432/dbpedidos");
            environment.getPropertySources().addFirst(
                new MapPropertySource("fallback-database-url", props)
            );
            return;
        }
        
        String normalizedUrl = normalizeUrl(dbUrl);
        System.out.println("DEBUG: Normalized URL: " + normalizedUrl);
        
        if (!normalizedUrl.equals(dbUrl)) {
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", normalizedUrl);
            environment.getPropertySources().addFirst(
                new MapPropertySource("corrected-database-url", props)
            );
        }
    }

    private String normalizeUrl(String url) {
        // Si ya tiene jdbc:, no agregar más
        if (url.startsWith("jdbc:")) {
            return url;
        }
        
        // Agregar jdbc: si no está
        if (url.startsWith("postgresql://")) {
            url = "jdbc:" + url;
        }
        
        // Agregar puerto :5432 si falta
        if (url.contains("@") && !url.matches(".*@[^/:]+:\\d+.*")) {
            int atIndex = url.lastIndexOf('@');
            int slashAfterHost = url.indexOf('/', atIndex);
            
            if (slashAfterHost > atIndex) {
                String beforeSlash = url.substring(0, slashAfterHost);
                String afterSlash = url.substring(slashAfterHost);
                url = beforeSlash + ":5432" + afterSlash;
            }
        }
        
        return url;
    }
}

