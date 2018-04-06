package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles({"datajpa", "datajpa,jpa"})
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

}