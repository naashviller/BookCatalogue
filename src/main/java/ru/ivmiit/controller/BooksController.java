package ru.ivmiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.service.AuthenticationService;
import ru.ivmiit.service.BooksService;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class BooksController {

    private final AuthenticationService authenticationService;
    private final BooksService booksService;

    @Autowired
    public BooksController(AuthenticationService authenticationService, BooksService booksService) {
        this.authenticationService = authenticationService;
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(booksService.getAllBooks());
    }

    @GetMapping("/books/search/booked")
    public ResponseEntity<List<Book>> getAllBooksBooked() {
        return ResponseEntity.ok(booksService.getBooksByBookStatusIsBooked());
    }

    @PostMapping("/books/add")
    public ResponseEntity<Object> addBook(@RequestBody BookForm form) {
        booksService.addBook(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books/edit/status")
    public ResponseEntity<Object> editBookStatus(@RequestBody EditBookStatusForm form)
            throws MessagingException {
        booksService.changeBookStatus(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books/notify/{id}/{userId}")
    public ResponseEntity<String> prepareEmail(@PathVariable("id") Long bookId,
                                               @PathVariable("userId") Long userId) {
        booksService.prepareNotificationEmail(bookId, userId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/books/search/title")
    public ResponseEntity<List<Book>> getBookByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(booksService.getBookByTitle(title));
    }

    @GetMapping("/books/search/genre")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam("genre") String genre) {
        return ResponseEntity.ok(booksService.getBooksByGenre(genre));
    }

    @GetMapping("/books/search/author")
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(booksService.getBooksByAuthor(author));
    }
}
