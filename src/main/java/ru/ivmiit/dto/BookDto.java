package ru.ivmiit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivmiit.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String genre;
    private String authorName;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre())
                .title(book.getTitle())
                .authorName(book.getAuthor().getName())
                .build();
    }

    public static List<BookDto> from(List<Book> books) {
        return books.stream().map(BookDto::from).collect(Collectors.toList());
    }
}
