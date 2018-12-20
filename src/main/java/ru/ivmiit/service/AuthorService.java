package ru.ivmiit.service;

import ru.ivmiit.model.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorByNameAndLastName(String name, String lastname);

    List<Author> getAllAuthors();
}
