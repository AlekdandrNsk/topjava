package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.DataJpaUserRepositoryImpl;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class UserFormValidator implements Validator {

    @Autowired
    DataJpaUserRepositoryImpl userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String email = ((UserTo) target).getEmail();
        User user = userRepository.getByEmail(email);
        if (user != null) {
            errors.rejectValue("email", "user.email.duplicate", " : user with this email already exists");
        }
    }
}