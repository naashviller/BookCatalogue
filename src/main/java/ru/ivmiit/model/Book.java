package ru.ivmiit.model;

import lombok.*;
import ru.ivmiit.model.enums.BookStatus;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "author")
@EqualsAndHashCode(exclude = "author")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
}
