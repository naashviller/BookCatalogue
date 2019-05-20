package ru.itis.forms;

import lombok.Data;
import ru.itis.model.enums.BookStatus;

@Data
public class EditBookStatusForm {
    public Long bookId;
    private BookStatus bookStatus;
    private String email;
}
