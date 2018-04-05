package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles({"jpa", "datajpa,jpa"})
public class JpaMealServiceTest extends AbstractMealServiceTest {

}