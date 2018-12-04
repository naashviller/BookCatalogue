package ru.ivmiit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.BookStatus;

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
