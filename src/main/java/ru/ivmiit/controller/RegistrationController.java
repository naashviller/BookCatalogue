package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.service.RegistrationService;

@RestController
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody LoginPasswordForm form) throws Exception {
        return ResponseEntity.ok(registrationService.register(form));
    }
}
