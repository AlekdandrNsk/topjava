package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"jpa", "datajpa,jpa"})
public class JpaMealServiceTest extends AbstractMealServiceTest {

}