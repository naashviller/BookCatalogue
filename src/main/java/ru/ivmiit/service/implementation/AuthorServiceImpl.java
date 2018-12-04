package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.model.Author;
import ru.ivmiit.repositories.AuthorsRepository;
import ru.ivmiit.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Author getAuthorByNameAndLastName(String name, String lastname) {
        return authorsRepository.getAuthorByNameAndLastName(name, lastname);
    }
}
