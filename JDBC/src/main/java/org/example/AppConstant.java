package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class AppConstant {
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String USER = "postgres";
    static final String PASSWORD = "postgres";
    static final HikariDataSource ds;
    static final HikariConfig config = new HikariConfig();
    static {
        config.setJdbcUrl(DATABASE_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setConnectionTimeout(50000);
        config.setMaximumPoolSize(100);
        ds = new HikariDataSource(config);
    }
}
