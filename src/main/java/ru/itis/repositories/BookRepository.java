package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.model.Book;
import ru.itis.model.enums.BookStatus;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findBookByTitle(@Param("title") String title);

    Book findBookById(Long id);

    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%'))")
    List<Book> findBooksByGenre(@Param("genre") String genre);

    @Query("SELECT b FROM Book b LEFT JOIN b.author a WHERE LOWER(CONCAT(a.name, ' ', a.lastName)) "
            + "LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Book> findBooksByAuthorName(@Param("name") String name);

    List<Book> findBooksByBookStatus(BookStatus status);

    List<Book> findBooksByBookStatusIs(BookStatus bookStatus);
}
