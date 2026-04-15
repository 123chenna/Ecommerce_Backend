package com.ecommerce.security;

import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATABASE_URL");

        if (dbUrl == null) {
            throw new RuntimeException("DATABASE_URL is not set");
        }

        String jdbcUrl = dbUrl.replace("postgresql://", "jdbc:postgresql://");

        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .build();
    }
}