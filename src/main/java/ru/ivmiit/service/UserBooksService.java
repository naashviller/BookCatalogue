package ru.ivmiit.service;


import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.UserBook;
import ru.ivmiit.model.enums.BookStatus;

import java.util.List;

public interface UserBooksService {

    void changeStatusBook(EditBookStatusForm editBookStatusForm);

    List<UserBook> getAllBooksBooked(BookStatus status);

}
