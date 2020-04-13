package com.thoughtworks.movietheater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tw-movie-theater?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        return dataSource;
    }
}
