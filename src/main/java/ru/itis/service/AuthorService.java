package ru.itis.service;

import ru.itis.model.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorByNameAndLastName(String name, String lastname);

    List<Author> getAllAuthors();
}
