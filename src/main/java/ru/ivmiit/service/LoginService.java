package ru.ivmiit.service;

import ru.ivmiit.dto.TokenDto;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.forms.ResetPasswordForm;
import ru.ivmiit.model.User;

public interface LoginService {

    User login(LoginPasswordForm loginPassword);

    TokenDto refreshToken();

    void resetPassword(ResetPasswordForm passwordForm);
}
