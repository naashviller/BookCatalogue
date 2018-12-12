package ru.ivmiit.service;

import ru.ivmiit.dto.UserDto;
import ru.ivmiit.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getUsers(String role);

    String getUserRoleByToken(String token);
}
