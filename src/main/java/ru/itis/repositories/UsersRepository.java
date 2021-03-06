package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.User;
import ru.itis.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByToken(String token);

    List<User> findAllByRole(Role role);

    Optional<User> findOneByLogin(String login);

    Optional<User> findByLogin(String login);
}
