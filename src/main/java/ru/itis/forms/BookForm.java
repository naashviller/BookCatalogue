package ru.itis.forms;

import lombok.Data;
import ru.itis.model.Author;

@Data
public class BookForm {
    private String title;
    private String genre;
    private Author author;
}
