package ru.ivmiit.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.ivmiit.security.authentications.TokenAuthentication;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("tokenUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // получили токен, который был  контексте безопасности
        TokenAuthentication tokenAuthentication =
                (TokenAuthentication) authentication;
        // нашли в бд данные о пользоваетеле и его правах
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(tokenAuthentication.getName());
        // добавили эти данные в объект аутентификации
        tokenAuthentication.setUserDetails(userDetails);
        // сказали, что с пользователем все нормально
        // и он аутентифицирован
        tokenAuthentication.setAuthenticated(true);
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
