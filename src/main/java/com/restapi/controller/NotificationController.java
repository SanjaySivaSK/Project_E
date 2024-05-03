package com.restapi.controller;


import com.restapi.model.Notifications;
import com.restapi.model.Role;
import com.restapi.request.NotificationRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
    @RequestMapping("/api/notification")

public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private APIResponse apiResponse;

//    @PostMapping("/send")
//    public ResponseEntity<APIResponse> Request(@RequestBody
//                                               NotificationRequest notificationRequest) {
//        List<Notifications> notifications = notificationService.Request(notificationRequest);
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(notifications);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    //admin
    @RolesAllowed({Role.ADMIN})
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> clear(@PathVariable Long id) {

        String RequestBook = notificationService.clear(id);
        System.out.println(RequestBook);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RequestBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //adminMessage
    @RolesAllowed({Role.ADMIN})
    @GetMapping("/message")
    public ResponseEntity<APIResponse> getNotification() {
        List<Notifications> notifications = notificationService.getNotification();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//userRenewal

    //AdminDeclineTheMessage
    @RolesAllowed({Role.ADMIN})
    @GetMapping("/Renewal/message")
    public ResponseEntity<APIResponse> getRenewalBook() {
        List<Notifications> notifications = notificationService.getRenewalBook();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //AdminDeclineTheMessage
    @RolesAllowed({Role.ADMIN})
    @PutMapping("/remove/{id}")
    public ResponseEntity<APIResponse> remove(@PathVariable Long id) {

        String RequestBook = notificationService.remove(id);
        System.out.println(RequestBook);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RequestBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //AdminDeclineTheMessage
    @RolesAllowed({Role.ADMIN})
    @PutMapping("/decline/{id}/message/{msg}")
    public ResponseEntity<APIResponse> decline(@PathVariable Long id, @PathVariable String msg) {

        String RequestBook = notificationService.decline(id, msg);
        System.out.println(msg + "decline msg ");
        System.out.println(RequestBook);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RequestBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @RolesAllowed({Role.USER})
    @PutMapping("/Renewal/{id}")
    public ResponseEntity<APIResponse> Renewal(@PathVariable Long id) {
        System.out.println(id + "dddd");
        String RenewalBook = notificationService.Renewal(id);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RenewalBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //UserAccepted
    @RolesAllowed({Role.USER})
    @PutMapping("/accept/{id}")
    public ResponseEntity<APIResponse> accept(@PathVariable Long id) {
        System.out.println(id + "accept");
        String RequestBook = notificationService.accept(id);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RequestBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //UserDeclinedBook
    @RolesAllowed({Role.USER})
    @GetMapping("/declined/{userId}")
    public ResponseEntity<APIResponse> declined(@PathVariable Long userId) {

        List<Notifications> declinedBooks = notificationService.declined(userId);
        System.out.println(declinedBooks + "hiii");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(declinedBooks);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//userBookRequest

    @PostMapping("/send")
    public ResponseEntity<APIResponse> Request(@RequestBody
                                               NotificationRequest notificationRequest) {
        List<Notifications> notifications = notificationService.Request(notificationRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/DeclinedBook")
    public ResponseEntity<APIResponse> getDeclinedBook() {
        List<Notifications> notifications = notificationService.adminDeclinedBook();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @GetMapping("/SuccessBook")
    public ResponseEntity<APIResponse> getSuccessBook() {
        List<Notifications> notifications = notificationService.adminSuccessBook();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}