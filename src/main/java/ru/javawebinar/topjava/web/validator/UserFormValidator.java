package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.HasId;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@Component
public class UserFormValidator implements Validator {

    @Autowired
    UserService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz) || User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasId oldUser;
        String email;
        if (target instanceof UserTo) {
            oldUser = (UserTo) target;
            email = ((UserTo) target).getEmail();
        } else {
            oldUser = (User) target;
            email = ((User) target).getEmail();
        }
        User user = null;
        try {
            user = service.getByEmail(email);
        } catch (NotFoundException e) {
        }
        if (user != null && !user.getId().equals(oldUser.getId())) {
            errors.rejectValue("email", "user.email.duplicate", " : user with this email already exists");
        }
    }
}