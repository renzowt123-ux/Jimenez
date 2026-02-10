package com.jimenez.pedido.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATABASE_URL");
        String dbUser = System.getenv("DATABASE_USER");
        String dbPassword = System.getenv("DATABASE_PASSWORD");
        
        System.out.println("========= DataSourceConfig Bean =========");
        System.out.println("DATABASE_URL: " + dbUrl);
        System.out.println("DATABASE_USER: " + dbUser);
        System.out.println("=========================================");
        
        // Si no hay DATABASE_URL, usar fallback
        if (dbUrl == null || dbUrl.isEmpty()) {
            System.out.println("USANDO FALLBACK: jdbc:postgresql://localhost:5432/dbpedidos");
            dbUrl = "jdbc:postgresql://localhost:5432/dbpedidos";
            dbUser = "postgres";
            dbPassword = "postgres";
        } else {
            // Normalizar la URL si es necesario
            if (!dbUrl.startsWith("jdbc:")) {
                if (dbUrl.startsWith("postgresql://")) {
                    dbUrl = "jdbc:" + dbUrl;
                }
            }
            
            // Agregar puerto si falta
            if (!dbUrl.matches(".*@[^/:]+:\\d+.*")) {
                int atIndex = dbUrl.lastIndexOf('@');
                int slashAfterHost = dbUrl.indexOf('/', atIndex);
                
                if (atIndex > 0 && slashAfterHost > atIndex) {
                    String beforeSlash = dbUrl.substring(0, slashAfterHost);
                    String afterSlash = dbUrl.substring(slashAfterHost);
                    dbUrl = beforeSlash + ":5432" + afterSlash;
                }
            }
            
            System.out.println("URL normalizada: " + dbUrl);
        }
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        if (dbUser != null && !dbUser.isEmpty()) {
            config.setUsername(dbUser);
        }
        if (dbPassword != null && !dbPassword.isEmpty()) {
            config.setPassword(dbPassword);
        }
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        
        return new HikariDataSource(config);
    }
}

