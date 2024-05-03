package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Book;
import com.restapi.model.Notifications;
import com.restapi.repository.BookRepository;
import com.restapi.repository.NotificationRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.BookRequest;
import com.restapi.request.NotificationRequest;
import com.restapi.response.UserHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Notifications> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notifications> Request(NotificationRequest notificationRequest) {
        Notifications notifications = new Notifications();


        AppUser appuser = userRepository.findById(notificationRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId",
                        "userId", notificationRequest.getUserId()));

        Book book = bookRepository.findById(notificationRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("bookId",
                        "bookId", notificationRequest.getBookId()));

        notifications.setAppUser(appuser);
        notifications.setBook(book);
        notifications.setRequest(true);
        notifications.setRenewal(false);

        notificationRepository.save(notifications);

        return findAll();
    }
//    public List<Notifications> accepted(NotificationRequest notificationRequest) {
//        Notifications notifications = new Notifications();
//
//
//        AppUser appuser = userRepository.findById(notificationRequest.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("userId",
//                        "userId", notificationRequest.getUserId()));
//
//        Book book = bookRepository.findById(notificationRequest.getBookId())
//                .orElseThrow(() -> new ResourceNotFoundException("bookId",
//                        "bookId", notificationRequest.getBookId()));
//
//        notifications.setAppUser(appuser);
//        notifications.setBook(book);
//        notifications.setRequest(false);
//        notificationRepository.save(notifications);
//
//        return findAll();
//    }

   public String clear(Long id) {
       notificationRepository.clear(id);
        return "success";
   }
    public List<Notifications> getNotification(){
       return notificationRepository.notifications();
    }


    public String Renewal(Long id) {
        notificationRepository.renewal(id);
        return "renewal";
    }

    public List<Notifications> getRenewalBook() {
        return notificationRepository.renewalBook();
    }
    public String remove(Long id) {
        notificationRepository.remove(id);
        return "success";
    }


    public String decline(Long id,String msg) {
        notificationRepository.decline(id,msg);
        return "decline";
    }

    public List<Notifications> declined(Long userId) {
        List<Notifications> declinedNotifications = notificationRepository.declined(userId);
        return declinedNotifications.stream()
                .filter(Notifications::isDecline)
                .collect(Collectors.toList());
    }

    public String accept(Long id) {
        notificationRepository.accept(id);
        return "accept";
    }
    public List<Notifications> adminDeclinedBook(){
        return notificationRepository.adminDeclinedBook();
    }
    public List<Notifications> adminSuccessBook(){
        return notificationRepository.adminSuccessBook();
    }
}

