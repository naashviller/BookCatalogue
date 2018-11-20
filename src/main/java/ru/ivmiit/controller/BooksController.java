package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.BookDto;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.service.BooksService;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/books")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestHeader("token") String token) {
        return ResponseEntity.ok(booksService.getAllBooks());
    }

    @PostMapping("/books")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addBook(@RequestBody BookForm form) {
        booksService.addBook(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books/edit/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> editBookStatus(@RequestBody EditBookStatusForm form) {
        booksService.changeBookStatus(form);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/search/title/{title}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Book> getBookByTitle(@PathVariable("title") String title, @RequestHeader("token") String token) {
        return ResponseEntity.ok(booksService.getBookByTitle(title));
    }

    @GetMapping("/books/search/genre/{genre}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Book>> getBookByGenre(@PathVariable("genre") String genre, @RequestHeader("token") String token) {
        return ResponseEntity.ok(booksService.getBooksByGenre(genre));
    }

    @GetMapping("/books/search/author/{authorId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Book> getBookByAuthor(@PathVariable("authorId") Integer authorId, @RequestHeader("token") String token) {
        return ResponseEntity.ok(booksService.getBookByAuthor(authorId));
    }
}
