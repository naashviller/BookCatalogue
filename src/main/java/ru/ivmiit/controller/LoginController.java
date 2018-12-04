package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.ivmiit.dto.TokenDto;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.forms.ResetPasswordForm;
import ru.ivmiit.service.LoginService;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginPasswordForm loginPassword) {
        System.out.println(loginPassword.getLogin());
        System.out.println(loginPassword.getPassword());
        return ResponseEntity.ok(loginService.login(loginPassword));
    }

    @PostMapping("/refresh")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TokenDto> refreshToken(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(loginService.refreshToken());
    }


    @PostMapping("/password")
    @PreAuthorize("isAuthenticated()")
    public void resetPassword(@RequestBody ResetPasswordForm passwordForm, @RequestHeader("Authorization") String authorization) {
        loginService.resetPassword(passwordForm);
    }
}