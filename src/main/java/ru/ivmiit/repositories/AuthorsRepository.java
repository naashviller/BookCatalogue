package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.model.Author;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {

    Author getAuthorById(Integer id);

}
