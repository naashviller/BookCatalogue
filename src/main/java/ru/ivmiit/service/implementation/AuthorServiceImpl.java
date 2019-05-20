package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.model.Author;
import ru.ivmiit.repositories.AuthorsRepository;
import ru.ivmiit.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorServiceImpl(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @Override
    public Author getAuthorByNameAndLastName(String name, String lastname) {
        return authorsRepository.getAuthorByNameAndLastName(name, lastname);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }
}
