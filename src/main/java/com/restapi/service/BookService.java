package com.restapi.service;

import com.restapi.dto.BookDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Author;
import com.restapi.model.Book;


import com.restapi.model.Category;
import com.restapi.repository.AuthorRepository;
import com.restapi.repository.BookRepository;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.BookRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDto bookDto;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StorageService storageService;

    public List<Book> FindAll() {

        return bookRepository.FindAll();
    }
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Transactional
    public List<Book> createBook(BookRequest bookRequest) {
        Book book = bookDto.mapToBook(bookRequest);
        Category category = null;


        Author author;

        category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", bookRequest.getCategoryId()));

        author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("AuthorId",
                        "AuthorId", bookRequest.getAuthorId()));

        book.setCategory(category);
        book.setAuthor(author);
        bookRepository.save(book);
        return FindAll();

    }

    public List<Book> updateBook(BookRequest bookRequest) {
        Book book = bookDto.mapToBook(bookRequest);
        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", bookRequest.getCategoryId()));
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("AuthorId",
                        "AuthorId", bookRequest.getAuthorId()));
        book.setCategory(category);
        book.setAuthor(author);
        Book existingBook = bookRepository.findById(bookRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("id",
               "id", bookRequest.getId()));

        book.setPhoto(existingBook.getPhoto());

        bookRepository.save(book);
        return FindAll();
    }

    public String DeleteById(Integer id) {
        bookRepository.DeleteById(Long.valueOf(id));
        return "deleted";


    }


//    public List<Book> findById(Long userId) {
//        bookRepository.findById(userId);
//        return FindAll();
//}



    public File getFile(Long id) throws IOException {
        Book book= bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(book.getPhoto());
        return resource.getFile();
    }

//
//    public List<Book> requestedBook() {
//        List<Book> notifications=bookRepository.notifications();
//        return  notifications ;
//    }

//    public List<Book> requestBook(Integer id) {
//        bookRepository.requestBooks(Long.valueOf(id));
//        return findAll();
//    }

}