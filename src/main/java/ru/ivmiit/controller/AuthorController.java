package ru.ivmiit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ivmiit.model.Author;
import ru.ivmiit.service.AuthorService;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/author")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Author> getAuthorByNameAndLastname(@RequestParam("name") String name, @RequestParam("lastname") String lastname) {
        return ResponseEntity.ok(authorService.getAuthorByNameAndLastName(name,lastname));
    }

}
