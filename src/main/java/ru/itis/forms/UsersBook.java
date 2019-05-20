package ru.itis.forms;

import lombok.Data;
import ru.itis.model.Book;
import ru.itis.model.User;
import ru.itis.model.enums.BookStatus;

@Data
public class UsersBook {
    private Long id;
    private User user;
    private Book book;
    private BookStatus bookStatus;
}
