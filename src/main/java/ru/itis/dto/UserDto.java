package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.User;
import ru.itis.model.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String login;
    private String username;
    private String email;
    private Role role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static List<UserDto> from(List<User> user) {
        return user.stream().map(UserDto::from).collect(Collectors.toList());
    }

}
