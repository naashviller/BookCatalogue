package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.UserBook;

public interface UserBooksRepository extends JpaRepository<UserBook, Integer> {

    UserBook findUserBookByBook(Book book);

}
