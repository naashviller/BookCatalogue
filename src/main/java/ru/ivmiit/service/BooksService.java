package ru.ivmiit.service;

import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.enums.BookStatus;

import java.util.List;

public interface BooksService {
    List<Book> getAllBooks();

    void addBook(BookForm form);

    void changeBookStatus(EditBookStatusForm form);

    Book getBookByTitle(String title);

    List<Book> getBooksByGenre(String genre);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByBookStatus(BookStatus status);

    List<Book> getBooksByBookStatusIsBooked();
}
