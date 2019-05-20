package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.Book;
import ru.itis.model.User;
import ru.itis.model.enums.BookStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBookDto {
    private Long id;
    private User user;
    private Book book;
    private BookStatus bookStatus;
}
