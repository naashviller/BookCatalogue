package ru.ivmiit.forms;

import lombok.Data;
import ru.ivmiit.model.Author;

@Data
public class BookForm {
    private String title;
    private String genre;
    private Author author;
}
