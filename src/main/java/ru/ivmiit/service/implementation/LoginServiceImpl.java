package ru.ivmiit.service.implementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.TokenDto;
import ru.ivmiit.forms.LoginPasswordForm;
import ru.ivmiit.forms.ResetPasswordForm;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.Role;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.service.AuthenticationService;
import ru.ivmiit.service.LoginService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expired.days}")
    private Integer jwtExpiredDays;

    @Override
    public TokenDto login(LoginPasswordForm loginPassword) {
        String rawPassword = loginPassword.getPassword();
        String login = loginPassword.getLogin().toLowerCase();

        User user = usersRepository.findOneByLogin(login).orElseThrow(()
                -> new BadCredentialsException("User login/password incorrect"));

        if (passwordEncoder.matches(rawPassword, user.getHashPassword()) && user.getRole().equals(Role.USER)) {
            return TokenDto.builder().token("JWT " + getTokenAsString(user)).build();
        } else throw new BadCredentialsException("User login/password incorrect");
    }

    private String getTokenAsString(User user) {
        return Jwts.builder()
                .claim("role", user.getRole().toString())
                .claim("login", user.getLogin())
                .setSubject(user.getId().toString())
                .setExpiration(Date.from(LocalDateTime.now()
                        .plusDays(jwtExpiredDays)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    @Override
    public TokenDto refreshToken() {
        User currentUser = authenticationService.getCurrentUser();
        return TokenDto.builder().token(getTokenAsString(currentUser)).build();
    }

    @Override
    public void resetPassword(ResetPasswordForm passwordForm) {
        Long currentUserId = authenticationService.getCurrentUser().getId();
        User currentUser = usersRepository.getOne(currentUserId);
        if (passwordEncoder.matches(passwordForm.getOldPassword(), currentUser.getHashPassword())) {
            currentUser.setHashPassword(passwordEncoder.encode(passwordForm.getNewPassword()));
            usersRepository.save(currentUser);
        } else throw new IllegalArgumentException("Wrong old password");
    }

}
