package ru.ivmiit.service;

import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.model.User;

public interface RegistrationService {
    User register(LoginPasswordForm form) throws Exception;
}
