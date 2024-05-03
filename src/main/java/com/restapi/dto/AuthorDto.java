package com.restapi.dto;

import com.restapi.model.Author;
import com.restapi.model.Category;
import com.restapi.request.AuthorRequest;
import com.restapi.request.CategoryRequest;
import com.restapi.response.AuthorResponse;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AuthorDto {

    public AuthorResponse mapToAuthorResponse(List<Author> authorList) {
        AuthorResponse authorResponse = new AuthorResponse();
        ArrayList<AuthorRequest> AuthorRequest = new ArrayList<>();
        for (Author author : authorList) {
            AuthorRequest.add(new AuthorRequest(author.getId(), author.getAuthor()));
        }
        authorResponse.setAuthors(AuthorRequest);
        return authorResponse;
    }

    public Author mapToAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        if (authorRequest.getId() != null) {
            author.setId(authorRequest.getId());
        }
        author.setAuthor(authorRequest.getAuthor());
        return author;
    }
}