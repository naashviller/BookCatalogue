package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.UserDto;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.Role;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.service.UserService;

import java.util.List;
import java.util.Optional;

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
}


