package ru.itis.service;


import ru.itis.forms.EditBookStatusForm;
import ru.itis.model.UserBook;
import ru.itis.model.enums.BookStatus;

import java.util.List;

public interface UserBooksService {

    void changeStatusBook(EditBookStatusForm editBookStatusForm);

    List<UserBook> getAllBooksBooked(BookStatus status);

}
