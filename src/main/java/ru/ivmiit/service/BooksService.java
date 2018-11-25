package ru.ivmiit.service;

import ru.ivmiit.dto.BookDto;
import ru.ivmiit.forms.BookForm;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;

import java.util.List;

public interface BooksService {
    List<BookDto> getAllBooks();


    void addBook(BookForm form);

    void changeBookStatus(EditBookStatusForm form);

    Book getBookByTitle(String title);

    List<Book> getBooksByGenre(String genre);

    Book getBookByAuthor(Long id);
}
