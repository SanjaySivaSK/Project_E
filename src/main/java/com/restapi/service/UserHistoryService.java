package com.restapi.service;

import com.restapi.dto.UserHistoryDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.BookRepository;
import com.restapi.repository.UserHistoryRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.UserHistoryRequest;
import com.restapi.response.UserHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserHistoryService {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserHistoryDto userHistoryDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<UserHistoryResponse> findAll() {
        List<UserHistory> userHistoryList = userHistoryRepository.findAll();
        return UserHistoryDto.mapToUserResponse(userHistoryList);
    }

    @Transactional
    public List<UserHistoryResponse> issueBook(UserHistoryRequest userHistoryRequest) {
        UserHistory userHistory = userHistoryDto.mapToBook(userHistoryRequest);
        AppUser appUser;
        Book book;

        appUser = userRepository.findById(userHistoryRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("UserId", "UserId", userHistoryRequest.getUserId()));

        book = bookRepository.findById(userHistoryRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("BookId", "BookId", userHistoryRequest.getBookId()));

        if (book.getStock() <= 0) {

            throw new RuntimeException("Book out of stock");
        }
        System.out.println(book+"sanj");
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        userHistory.setAppUser(appUser);
        userHistory.setBook(book);
        userHistory.setReturned(false);


        userHistoryRepository.save(userHistory);

        return findAll();
    }

    public List<UserHistoryResponse> updateBook(UserHistoryRequest userHistoryRequest) {
        UserHistory userHistory = userHistoryDto.mapToBook(userHistoryRequest);
        AppUser appUser;
        Book book;

        appUser = userRepository.findById(userHistoryRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("UserId", "UserId", userHistoryRequest.getUserId()));

        book = bookRepository.findById(userHistoryRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("BookId", "BookId", userHistoryRequest.getBookId()));



        userHistory.setAppUser(appUser);
        userHistory.setBook(book);

        userHistoryRepository.save(userHistory);
        return findAll();
    }

    public List<UserHistoryResponse> findById(Long userId) {
        List<UserHistory> userHistoryList = userHistoryRepository.findByUserId(userId);
        return UserHistoryDto.mapToUserResponse(userHistoryList);
    }

    public String returnBooks(Long id, Long fineAmount,Long BookId) {
        userHistoryRepository.returnBook(id, fineAmount);
        Book book;
        book = bookRepository.findById(BookId)
                .orElseThrow(() -> new ResourceNotFoundException("BookId", "BookId", BookId));
        System.out.println(book.getId());
        book.setStock(book.getStock()+1);
        bookRepository.save(book);
        return "success";
    }

    public List<UserHistory> returnedBook() {
        List<UserHistory> returnedBook = userHistoryRepository.returnedBook();

        return returnedBook;
    }

    public String Renewal(Long id) {
        userHistoryRepository.renewal(id);
        return "renewal";
    }

    public List<UserHistory> getRenewalBook() {
        return userHistoryRepository.renewalBook();
    }

    public String remove(Long id) {
        userHistoryRepository.remove(id);
        return "remove";
    }
//    public String decline(Long id) {
//        userHistoryRepository.decline(id);
//        return "decline";}

}
