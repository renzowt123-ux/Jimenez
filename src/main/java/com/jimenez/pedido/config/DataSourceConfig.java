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

        String databaseUrl = System.getenv("DATABASE_URL");
        HikariConfig config = new HikariConfig();

        // Opción 1: Si existe DATABASE_URL (compatibilidad)
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            if (!databaseUrl.startsWith("jdbc:")) {
                databaseUrl = "jdbc:" + databaseUrl;
            }
            System.out.println(">>> Using DATABASE_URL");
            config.setJdbcUrl(databaseUrl);
        } 
        // Opción 2: Si vienen variables individuales (Render con Docker)
        else {
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbHost != null && !dbHost.isEmpty()) {
                dbPort = (dbPort != null && !dbPort.isEmpty()) ? dbPort : "5432";
                databaseUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
                
                System.out.println(">>> Using individual DB variables from Render");
                config.setJdbcUrl(databaseUrl);
                config.setUsername(dbUser);
                config.setPassword(dbPassword);
            } 
            // Opción 3: Fallback local
            else {
                System.out.println(">>> Using LOCAL PostgreSQL");
                config.setJdbcUrl("jdbc:postgresql://localhost:5432/dbpedidos");
                config.setUsername("postgres");
                config.setPassword("postgres");
            }
        }

        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);

        return new HikariDataSource(config);
    }
}

