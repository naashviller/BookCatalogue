package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Author getAuthorByNameAndLastName(String name, String lastname);

}
