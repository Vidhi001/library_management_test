package com.example.minor_project1.dtos;


import com.example.minor_project1.models.Author;
import com.example.minor_project1.models.Book;
import com.example.minor_project1.models.Genre;
import com.example.minor_project1.models.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String bookName;

    @NotNull
    private Genre genre;

    private Author my_author;

    private Student my_student;


    public Book to(){

        return Book.builder()
                .bookName(this.bookName)
                .genre(this.genre)
                .my_author(this.my_author)
                .my_student(this.my_student)
                .build();
    }

}
