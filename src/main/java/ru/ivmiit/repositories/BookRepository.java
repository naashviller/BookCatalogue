package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.model.Author;
import ru.ivmiit.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByTitle(String title);

    Book findBookById(Integer id);

    List<Book> findBooksByGenre(String genre);

    Book findBookByAuthor(Author author);

}
