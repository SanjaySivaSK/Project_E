package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookRequest {

    private Long id;

    @NotNull
    private Long categoryId;


    @Size(min = 5, message = "book-name  should have at least 5 characters")
    private String book;

    @NotEmpty
    private String description;

    @NotNull
        private Long authorId;

        private String photo;

        private int Stock;



    public BookRequest(String book) {
        this.book = book;
    }

}
