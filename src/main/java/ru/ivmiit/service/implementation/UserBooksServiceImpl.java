package ru.ivmiit.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ivmiit.forms.EditBookStatusForm;
import ru.ivmiit.repositories.UserBooksRepository;
import ru.ivmiit.service.UserBooksService;

public class UserBooksServiceImpl implements UserBooksService{

    @Autowired
    private UserBooksRepository userBooksRepository;


    @Override
    public void changeStatusBook(EditBookStatusForm editBookStatusForm) {



    }
}
