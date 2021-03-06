package ru.itis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.itis.model.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties

@Entity
@Table(name = "catalogue_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String hashPassword;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private Set<UserBook> userBookSet;

    @Column(name = "token")
    private String token;

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

    public boolean isReader() {
        return this.role.equals(Role.READER);
    }

    public boolean isUser() {
        return this.role.equals(Role.USER);
    }
}
