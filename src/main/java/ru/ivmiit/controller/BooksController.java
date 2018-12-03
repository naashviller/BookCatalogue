package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.BookDto;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.enums.BookStatus;
import ru.ivmiit.service.BooksService;

import java.util.List;

@RestController
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookDto>> getAllBooks() {
    //public ResponseEntity<List<BookDto>> getAllBooks(@RequestHeader("token") String token) {
        return ResponseEntity.ok(booksService.getAllBooks());
    }

    @GetMapping("/books/search/status")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Book>> getAllBooksBooked(@PathVariable("status") BookStatus status) {
        return ResponseEntity.ok(booksService.getBooksByBookStatus(status));
    }

    @PostMapping("/addBooks")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addBook(@RequestBody BookForm form) {
        //public ResponseEntity<Object> addBook(@RequestBody BookForm form, @RequestHeader("token") String token) {
            booksService.addBook(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books/edit/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> editBookStatus(@RequestBody EditBookStatusForm form) {
        booksService.changeBookStatus(form);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/search/title")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Book> getBookByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(booksService.getBookByTitle(title));
    }

    @GetMapping("/books/search/genre")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam("genre") String genre) {
        return ResponseEntity.ok(booksService.getBooksByGenre(genre));
    }

    @GetMapping("/books/search/author")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(booksService.getBooksByAuthor(author));
    }

}
