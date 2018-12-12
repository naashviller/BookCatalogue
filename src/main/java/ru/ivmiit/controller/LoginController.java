package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.TokenDto;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.forms.ResetPasswordForm;
import ru.ivmiit.model.User;
import ru.ivmiit.service.AuthenticationService;
import ru.ivmiit.service.LoginService;
import ru.ivmiit.service.UserService;

@RestController
public class LoginController {
    private final AuthenticationService authenticationService;
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationService authenticationService,
                           LoginService loginService, UserService userService) {
        this.authenticationService = authenticationService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginPasswordForm loginPassword) {
        System.out.println(loginPassword.getLogin());
        System.out.println(loginPassword.getPassword());
        return ResponseEntity.ok(loginService.login(loginPassword));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshToken(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(loginService.refreshToken());
    }


    @PostMapping("/password")
    public void resetPassword(@RequestBody ResetPasswordForm passwordForm, @RequestHeader("Authorization") String authorization) {
        loginService.resetPassword(passwordForm);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> getAuthenticatedUserToken() {
        return ResponseEntity.ok(authenticationService.getCurrentUser().getToken());
    }

    @GetMapping("/user/{token}")
    public ResponseEntity<String> getAuthenticatedUserData(@PathVariable("token") String token) {
        return ResponseEntity.ok(userService.getUserRoleByToken(token));
    }
}