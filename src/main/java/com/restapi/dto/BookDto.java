package com.restapi.dto;

import com.restapi.model.Book;
import com.restapi.request.BookRequest;
import com.restapi.response.BookResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookDto {
    public static BookResponse mapToCategoryResponse(List<Book> books) {
        return new BookResponse(books);
    }

    public Book mapToBook(BookRequest bookRequest) {
        Book book = new Book();
        if (bookRequest.getId() != null) {
            book.setId(bookRequest.getId());

        }
        book.setBook(bookRequest.getBook(   ));
        book.setDescription(bookRequest.getDescription());
        book.setPhoto(bookRequest.getPhoto());
        book.setStock(bookRequest.getStock());
        return book;
    }
}
