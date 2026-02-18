package com.jimenez.pedido.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @Profile("!test")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        String databaseUrl = firstNonBlank(
                System.getenv("DATABASE_URL"),
                System.getenv("SPRING_DATASOURCE_URL")
        );

        if (isNotBlank(databaseUrl)) {
            String jdbcUrl = normalizeJdbcUrl(databaseUrl);
            System.out.println(">>> Using DATABASE_URL/SPRING_DATASOURCE_URL");
            config.setJdbcUrl(jdbcUrl);

            String springUser = System.getenv("SPRING_DATASOURCE_USERNAME");
            String springPassword = System.getenv("SPRING_DATASOURCE_PASSWORD");
            if (isNotBlank(springUser)) {
                config.setUsername(springUser);
            }
            if (isNotBlank(springPassword)) {
                config.setPassword(springPassword);
            }
        } else {
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (isNotBlank(dbHost)) {
                dbPort = isNotBlank(dbPort) ? dbPort : "5432";
                databaseUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

                System.out.println(">>> Using DB_HOST/DB_PORT/DB_NAME variables");
                config.setJdbcUrl(databaseUrl);
                config.setUsername(dbUser);
                config.setPassword(dbPassword);
            } else {
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

    private String normalizeJdbcUrl(String rawUrl) {
        String url = rawUrl.trim();

        if (url.startsWith("jdbc:postgresql://")) {
            return url;
        }

        if (url.startsWith("postgresql://")) {
            return "jdbc:" + url;
        }

        if (url.startsWith("postgres://")) {
            return "jdbc:postgresql://" + url.substring("postgres://".length());
        }

        throw new IllegalArgumentException(
                "Unsupported DATABASE_URL format. Use postgres:// or postgresql://"
        );
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    private String firstNonBlank(String... values) {
        for (String value : values) {
            if (isNotBlank(value)) {
                return value;
            }
        }
        return null;
    }
}
