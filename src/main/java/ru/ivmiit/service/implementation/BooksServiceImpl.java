package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Author;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.UserBook;
import ru.ivmiit.model.enums.BookStatus;
import ru.ivmiit.repositories.AuthorsRepository;
import ru.ivmiit.repositories.BookRepository;
import ru.ivmiit.repositories.UserBooksRepository;
import ru.ivmiit.service.BooksService;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final AuthorsRepository authorsRepository;
    private final UserBooksRepository userBooksRepository;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository, AuthorsRepository authorsRepository, UserBooksRepository userBooksRepository) {
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
        this.userBooksRepository = userBooksRepository;
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
    public void changeBookStatus(EditBookStatusForm form) {
        UserBook userBook = userBooksRepository.findUserBookByBook(bookRepository.findBookById(form.getBookId()));
        userBook.setBookStatus(form.getBookStatus());
        userBooksRepository.save(userBook);
    }


    @Override
    public Book getBookByTitle(String title) {
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
    public List<Book> getBooksByBookStatus(BookStatus status){
        return bookRepository.findBooksByBookStatus(status);
    }

    @Override
    public List<Book> getBooksByBookStatusIsBooked() {
        return bookRepository.findBooksByBookStatusIs(BookStatus.BOOKED);
    }
}
