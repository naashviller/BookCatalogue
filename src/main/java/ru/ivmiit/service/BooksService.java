package ru.ivmiit.service;

import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.BookStatus;

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
