package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@Profile("hsqldb")
public class HsqldbJdbcMealRepositoryImpl extends AbstractJdbcMealRepositoryImpl {

    @Override
    protected Timestamp getProperDate(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }


}
