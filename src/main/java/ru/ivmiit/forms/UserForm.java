package ru.ivmiit.forms;

import lombok.Data;
import ru.ivmiit.model.enums.Role;

@Data
public class UserForm {
    private Long id;
    private String login;
    private String username;
    private Role role;
}
