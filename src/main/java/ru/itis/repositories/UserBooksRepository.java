package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Book;
import ru.itis.model.User;
import ru.itis.model.UserBook;
import ru.itis.model.enums.BookStatus;

import java.util.List;

public interface UserBooksRepository extends JpaRepository<UserBook, Integer> {

    UserBook findByBookAndUser(Book book, User user);

    UserBook findUserBookByBook(Book book);

    List<UserBook> findUserBookByBookStatus(BookStatus status);
}
