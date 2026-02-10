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
        // Intentar leer DATABASE_URL (Render)
        String dbUrl = System.getenv("DATABASE_URL");
        String dbUser = System.getenv("DATABASE_USER");
        String dbPassword = System.getenv("DATABASE_PASSWORD");
        
        // Si no hay DATABASE_URL, construir desde variables individuales
        if (dbUrl == null || dbUrl.isEmpty()) {
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            dbUser = System.getenv("DB_USER");
            dbPassword = System.getenv("DB_PASSWORD");
            
            if (dbHost != null && !dbHost.isEmpty()) {
                dbPort = (dbPort != null && !dbPort.isEmpty()) ? dbPort : "5432";
                dbUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
            } else {
                // Fallback local
                dbUrl = "jdbc:postgresql://localhost:5432/dbpedidos";
                dbUser = "postgres";
                dbPassword = "postgres";
            }
        } else {
            // Normalizar DATABASE_URL si es necesario
            if (!dbUrl.startsWith("jdbc:")) {
                dbUrl = "jdbc:" + dbUrl;
            }
        }
        
        System.out.println("========= DataSourceConfig Bean =========");
        System.out.println("DATABASE_URL: " + (dbUrl != null ? "***" : "null"));
        System.out.println("DATABASE_USER: " + dbUser);
        System.out.println("=========================================");
        
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

