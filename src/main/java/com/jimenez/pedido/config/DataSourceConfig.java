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
        String profile = environment.getProperty("spring.profiles.active");
        
        System.out.println("========= DataSourceConfig =========");
        System.out.println("Profile: " + profile);
        System.out.println("DATABASE_URL: " + dbUrl);
        System.out.println("====================================");
        
        // Solo procesar en prod
        if (!"prod".equals(profile)) {
            return;
        }
        
        // Si no hay URL o contiene variable sin resolver
        if (dbUrl == null || dbUrl.isEmpty() || dbUrl.contains("${") || !dbUrl.startsWith("jdbc:")) {
            System.out.println("FALLBACK: Usando fallback a localhost");
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", "jdbc:postgresql://localhost:5432/dbpedidos");
            environment.getPropertySources().addFirst(
                new MapPropertySource("fallback-database-url", props)
            );
            return;
        }
        
        // Normalizar URL
        String normalizedUrl = normalizeUrl(dbUrl);
        System.out.println("Normalized URL: " + normalizedUrl);
        
        if (!normalizedUrl.equals(dbUrl)) {
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", normalizedUrl);
            environment.getPropertySources().addFirst(
                new MapPropertySource("corrected-database-url", props)
            );
        }
    }

    private String normalizeUrl(String url) {
        // Si ya tiene jdbc:, verificar y ajustar si es necesario
        if (url.startsWith("jdbc:postgresql://")) {
            // Agregar puerto :5432 si falta
            if (!url.matches(".*@[^/:]+:\\d+.*")) {
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
        
        // Si es postgresql:// sin jdbc:, agregar jdbc:
        if (url.startsWith("postgresql://")) {
            url = "jdbc:" + url;
            return normalizeUrl(url); // Recursivamente normalizador
        }
        
        return url;
    }
}

