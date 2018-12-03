package ru.ivmiit.forms;

import lombok.Data;

@Data
public class BookForm {
    private String title;
    private String genre;
    private Long authorId;

}
