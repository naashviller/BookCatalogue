package ru.itis.service;

import ru.itis.forms.LoginPasswordForm;
import ru.itis.model.User;

public interface RegistrationService {
    User register(LoginPasswordForm form) throws Exception;
}
