package ru.itis.forms;

import lombok.Data;

@Data
public class ResetPasswordForm {
    private String oldPassword;
    private String newPassword;
}
