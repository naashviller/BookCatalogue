package ru.itis.service;

import ru.itis.dto.TokenDto;
import ru.itis.forms.LoginPasswordForm;
import ru.itis.forms.ResetPasswordForm;
import ru.itis.model.User;

public interface LoginService {

    User login(LoginPasswordForm loginPassword);

    TokenDto refreshToken();

    void resetPassword(ResetPasswordForm passwordForm);
}
