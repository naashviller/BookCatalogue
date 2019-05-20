package ru.itis.service;

import ru.itis.forms.BookForm;
import ru.itis.forms.EditBookStatusForm;
import ru.itis.model.Book;
import ru.itis.model.User;
import ru.itis.model.enums.BookStatus;

import javax.mail.MessagingException;
import java.util.List;

public interface BooksService {
    List<Book> getAllBooks();

    void addBook(BookForm form);

    void changeBookStatus(EditBookStatusForm form) throws MessagingException;

    List<Book> getBookByTitle(String title);

    List<Book> getBooksByGenre(String genre);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByBookStatus(BookStatus status);

    List<Book> getBooksByBookStatusIsBooked();

    void prepareNotificationEmail(Long bookId, Long userId);

    void sendEmail(User user, Book book) throws MessagingException;
}
