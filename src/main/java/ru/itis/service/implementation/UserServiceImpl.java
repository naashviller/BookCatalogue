package ru.itis.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.UserDto;
import ru.itis.model.User;
import ru.itis.model.enums.Role;
import ru.itis.repositories.UsersRepository;
import ru.itis.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getUsers(String role) {
        return UserDto.from(usersRepository.findAllByRole(Role.valueOf(role)));
    }

    @Override
    public String getUserRoleByToken(String token) {
        System.out.println(token);
        return usersRepository.findByToken(token).get().getRole().toString();
    }

    @Override
    public UserDto getUserByToken(String token) {
        User user = usersRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        return UserDto.from(user);
    }
}


