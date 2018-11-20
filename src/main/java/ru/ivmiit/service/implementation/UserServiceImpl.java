package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.UserDto;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.Role;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private AuthenticationImpl authentication;

    @Override
    public List<UserDto> getUsers(String role) {
        return UserDto.from(usersRepository.findAllByRole(Role.valueOf(role)));
    }

//    @Override
//    public UserDto getUserById(String userId) {
//        User current = authentication.getCurrentUser();
//        User user = usersRepository.getOne(Integer.valueOf(userId));
//        if (current.isAdmin()) {
//            return UserDto.from(user);
//        } else if (current.isReader() && current.getId().equals(user.getId())) {
//            return UserDto.from(user);
//        } else if (current.isUser() && current.getId().equals(user.getId())) {
//            return UserDto.from(user);
//        } else throw new IllegalArgumentException("user not found");
//    }
}


