package ru.ivmiit.forms;

import lombok.Data;
import ru.ivmiit.model.User;
import ru.ivmiit.model.enums.BookStatus;

@Data
public class EditBookStatusForm {
    public Integer bookId;
    private BookStatus bookStatus;

}
