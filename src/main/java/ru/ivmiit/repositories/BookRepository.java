package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.model.Author;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.enums.BookStatus;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);

    Book findBookById(Long id);

    List<Book> findBooksByGenre(String genre);

    List<Book> findBooksByAuthorName(String name);

    List<Book> findBooksByBookStatus(BookStatus status);
}
