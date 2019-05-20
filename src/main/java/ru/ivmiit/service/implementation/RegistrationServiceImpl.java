package ru.ivmiit.service.implementation;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.Role;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public RegistrationServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User register(LoginPasswordForm form) throws Exception {
        if (usersRepository.findOneByLogin(form.getLogin()).isPresent()) {
            throw new Exception("User with this email already exists");
        } else {
            if (form.getLogin() != null && form.getPassword() != null) {
                User user = User.builder()
                        .login(form.getLogin())
                        .email(form.getEmail())
                        .token(RandomStringUtils.randomAlphanumeric(10))
                        .hashPassword(encoder.encode(form.getPassword()))
                        .role(Role.USER)
                        .build();
                System.out.println("WE ARE IN REG SERVICE");
                usersRepository.save(user);
                return user;
            }
        }
        return null;
    }
}
