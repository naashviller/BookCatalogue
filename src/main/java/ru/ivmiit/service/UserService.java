package ru.ivmiit.service;

import ru.ivmiit.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(String role);

    String getUserRoleByToken(String token);

    UserDto getUserByToken(String token);
}
