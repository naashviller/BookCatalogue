package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Author;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.User;
import ru.ivmiit.model.UserBook;
import ru.ivmiit.model.enums.BookStatus;
import ru.ivmiit.repositories.AuthorsRepository;
import ru.ivmiit.repositories.BookRepository;
import ru.ivmiit.repositories.UserBooksRepository;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.service.BooksService;
import ru.ivmiit.service.SmtpMailSender;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final AuthorsRepository authorsRepository;
    private final UserBooksRepository userBooksRepository;
    private final UsersRepository usersRepository;
    private final SmtpMailSender mailSender;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository, AuthorsRepository authorsRepository,
                            UserBooksRepository userBooksRepository, UsersRepository usersRepository,
                            SmtpMailSender mailSender) {
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
        this.userBooksRepository = userBooksRepository;
        this.usersRepository = usersRepository;
        this.mailSender = mailSender;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(BookForm form) {
        Author author = authorsRepository.getAuthorByNameAndLastName(
                form.getAuthor().getName(), form.getAuthor().getLastName());

        Book book = Book.builder()
                .author(author)
                .genre(form.getGenre())
                .title(form.getTitle())
                .bookStatus(BookStatus.FREE)
                .build();

        bookRepository.save(book);
    }

    @Override
    public void changeBookStatus(EditBookStatusForm form) throws MessagingException {
        Book book = bookRepository.findBookById(form.getBookId());

        if (form.getBookStatus().equals(BookStatus.FREE)) {
            UserBook userBook = userBooksRepository.findUserBookByBook(book);
            if (!(userBook == null)) {
                sendEmail(userBook.getUser(), userBook.getBook());
            }
        }

        book.setBookStatus(form.getBookStatus());
        bookRepository.save(book);
    }


    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthorName(author);
    }

    @Override
    public List<Book> getBooksByBookStatus(BookStatus status) {
        return bookRepository.findBooksByBookStatus(status);
    }

    @Override
    public List<Book> getBooksByBookStatusIsBooked() {
        return bookRepository.findBooksByBookStatusIs(BookStatus.BOOKED);
    }

    @Override
    public void prepareNotificationEmail(Long bookId, Long userId) {
        Book book = bookRepository.findBookById(bookId);
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id not found"));

        if (book.getBookStatus().equals(BookStatus.BOOKED)) {
            UserBook userBook = UserBook.builder()
                    .book(book)
                    .user(user)
                    .build();
            userBooksRepository.save(userBook);
        }
    }

    @Override
    public void sendEmail(User user, Book book) throws MessagingException {
        UserBook userBook = userBooksRepository.findByBookAndUser(book, user);

        userBooksRepository.delete(userBook);

        mailSender.send(user.getEmail(), "Книга появилась в библиотеке",
                "Книга " + book.getTitle() + " появилась у нас в библиотеке");
    }
}
