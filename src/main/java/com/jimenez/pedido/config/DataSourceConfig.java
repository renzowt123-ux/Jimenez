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

        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            if (!databaseUrl.startsWith("jdbc:")) {
                databaseUrl = "jdbc:" + databaseUrl;
            }

            System.out.println(">>> Using DATABASE_URL from Render");
            config.setJdbcUrl(databaseUrl);

        } else {
            System.out.println(">>> Using LOCAL PostgreSQL");

            config.setJdbcUrl("jdbc:postgresql://localhost:5432/dbpedidos");
            config.setUsername("postgres");
            config.setPassword("postgres");
        }

        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);

        return new HikariDataSource(config);
    }
}

