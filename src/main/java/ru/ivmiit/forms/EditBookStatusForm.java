package ru.ivmiit.forms;

import lombok.Data;
import ru.ivmiit.model.enums.BookStatus;

@Data
public class EditBookStatusForm {
    public Long bookId;
    private BookStatus bookStatus;
    private String email;
}
