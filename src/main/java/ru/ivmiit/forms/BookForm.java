package ru.ivmiit.forms;

import lombok.Data;

@Data
public class BookForm {
    private Integer id;
    private String title;
    private String genre;
    private Integer authorId;
}
