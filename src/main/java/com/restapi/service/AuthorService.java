package com.restapi.service;

import com.restapi.dto.AuthorDto;
import com.restapi.model.Author;
import com.restapi.model.Category;
import com.restapi.repository.AuthorRepository;
import com.restapi.request.AuthorRequest;
import com.restapi.request.CategoryRequest;
import com.restapi.response.AuthorResponse;
import com.restapi.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorDto authorDto;

    public AuthorResponse findAll() {
        List<Author> authorList = authorRepository.findAll();
        return authorDto.mapToAuthorResponse(authorList);
    }
    public AuthorResponse create(AuthorRequest authorRequest) {
        Author author = authorDto.mapToAuthor(authorRequest);
        authorRepository.save(author);
        return findAll();
    }

    public AuthorResponse update(AuthorRequest authorRequest) {
        Author author = authorDto.  mapToAuthor(authorRequest);
        authorRepository.save(author);
        return findAll();
    }

    public AuthorResponse deleteById(Integer id) {
        authorRepository.deleteById(Long.valueOf(id));
        return findAll();
    }


}
