package ru.itis.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.forms.EditBookStatusForm;
import ru.itis.model.UserBook;
import ru.itis.model.enums.BookStatus;
import ru.itis.repositories.UserBooksRepository;
import ru.itis.service.UserBooksService;

import java.util.List;

@Service
public class UserBooksServiceImpl implements UserBooksService {

    @Autowired
    private UserBooksRepository userBooksRepository;


    @Override
    public void changeStatusBook(EditBookStatusForm editBookStatusForm) {


    }

    @Override
    public List<UserBook> getAllBooksBooked(BookStatus status) {
        return userBooksRepository.findUserBookByBookStatus(status);
    }


}
