package ru.itis.service.implementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.TokenDto;
import ru.itis.forms.LoginPasswordForm;
import ru.itis.forms.ResetPasswordForm;
import ru.itis.model.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.service.AuthenticationService;
import ru.itis.service.LoginService;

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
    public User login(LoginPasswordForm loginPassword) {
        String rawPassword = loginPassword.getPassword();
        String login = loginPassword.getLogin().toLowerCase();

        User user = usersRepository.findByLogin(login).orElseThrow(()
                -> new BadCredentialsException("User login/password incorrect"));

        if (user == null) {
            return null;
        }
        if (passwordEncoder.matches(rawPassword, user.getHashPassword())) {
            return usersRepository.findOneByLogin(login).get();
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
