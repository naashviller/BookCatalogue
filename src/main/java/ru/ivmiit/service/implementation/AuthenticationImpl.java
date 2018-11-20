//package ru.ivmiit.service.implementation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import ru.ivmiit.model.User;
//import ru.ivmiit.repositories.UsersRepository;
//import ru.ivmiit.security.details.UserDetailsImpl;
//import ru.ivmiit.service.AuthenticationService;
//
//@Service
//public class AuthenticationImpl implements AuthenticationService{
//
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Autowired
//    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Integer userId = ((UserDetailsImpl)authentication.getDetails()).getUser().getId();
//        return usersRepository.getOne(userId);
//    }
//
//}
