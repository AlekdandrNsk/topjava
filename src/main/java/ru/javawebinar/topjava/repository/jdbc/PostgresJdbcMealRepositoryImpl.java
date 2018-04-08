package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Profile("postgres")
public class PostgresJdbcMealRepositoryImpl extends AbstractJdbcMealRepositoryImpl {

    @Override
    protected LocalDateTime getProperDate(LocalDateTime dateTime) {
        return dateTime;
    }


}
