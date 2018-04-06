package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"datajpa", "datajpa,jpa"})
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

}