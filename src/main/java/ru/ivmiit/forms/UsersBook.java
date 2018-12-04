package ru.ivmiit.forms;

import lombok.Data;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.BookStatus;

@Data
public class UsersBook {
    private Long id;
    private User user;
    private Book book;
    private BookStatus bookStatus;
}
