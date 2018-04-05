package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles({"jpa", "datajpa,jpa"})
public class JpaUserServiceTest extends AbstractUserServiceTest {

}