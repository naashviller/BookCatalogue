package ru.ivmiit.service;

import ru.ivmiit.model.Author;

public interface AuthorService {
    Author getAuthorByNameAndLastName(String name, String lastname);

}
