package ru.itis.service;

import ru.itis.model.User;

public interface AuthenticationService {
    User getCurrentUser();
}
