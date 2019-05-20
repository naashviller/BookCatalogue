package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.TokenDto;
import ru.itis.dto.UserDto;
import ru.itis.forms.LoginPasswordForm;
import ru.itis.forms.ResetPasswordForm;
import ru.itis.model.User;
import ru.itis.service.AuthenticationService;
import ru.itis.service.LoginService;
import ru.itis.service.UserService;

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
        return ResponseEntity.ok(loginService.login(loginPassword));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshToken(
            @RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(loginService.refreshToken());
    }


    @PostMapping("/password")
    public void resetPassword(@RequestBody ResetPasswordForm passwordForm,
                              @RequestHeader("Authorization") String authorization) {
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

    @GetMapping("/user/{token}/all")
    public ResponseEntity<UserDto> getAuthenticatedUser(@PathVariable("token") String token) {
        return ResponseEntity.ok(userService.getUserByToken(token));
    }
}
