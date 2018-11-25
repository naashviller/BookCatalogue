package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.BookDto;
import ru.ivmiit.dto.UsersBookDto;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.model.Book;
import ru.ivmiit.model.UserBook;
import ru.ivmiit.model.enums.BookStatus;
import ru.ivmiit.repositories.UserBooksRepository;
import ru.ivmiit.service.UserBooksService;

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
