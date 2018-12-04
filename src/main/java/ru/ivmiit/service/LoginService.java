package ru.ivmiit.service;

import ru.ivmiit.dto.TokenDto;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.forms.ResetPasswordForm;

public interface LoginService {

    TokenDto login(LoginPasswordForm loginPassword);

    TokenDto refreshToken();

    void resetPassword(ResetPasswordForm passwordForm);
}
