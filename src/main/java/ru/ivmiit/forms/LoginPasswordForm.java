package ru.ivmiit.forms;

import lombok.Data;

@Data
public class LoginPasswordForm {

    private String login;
    private String password;
    private String email;

}
