package com.restapi.controller.admin;

import com.restapi.model.Notifications;
import com.restapi.model.Role;
import com.restapi.model.UserHistory;
import com.restapi.request.UserHistoryRequest;
import com.restapi.response.UserHistoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.NotificationService;
import com.restapi.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user-history")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class  AdminUserHistoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserHistoryService userHistoryService;


    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllHistory() {
        List<UserHistoryResponse> userHistoryResponses=userHistoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userHistoryResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
            @PostMapping("/issue-book")
    public ResponseEntity<APIResponse> issueBook(@RequestBody UserHistoryRequest userHistoryRequest) {
        List<UserHistoryResponse> userHistoryList = userHistoryService.issueBook(userHistoryRequest);
                System.out.println(userHistoryRequest.getIssuedDate()+"hi this for checkiing");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userHistoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/return-book")
    public ResponseEntity<APIResponse> updateBook(@RequestBody UserHistoryRequest userHistoryRequest) {
        List<UserHistoryResponse> userHistoryList = userHistoryService.updateBook(userHistoryRequest);
        System.out.println(userHistoryRequest.getUserId()+"dsss");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userHistoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/Renewal/message")
    public ResponseEntity<APIResponse> getRenewalBook() {
        List<UserHistory> notifications = userHistoryService.getRenewalBook();
        System.out.println(notifications);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notifications);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/remove/{id}")
    public ResponseEntity<APIResponse> remove(@PathVariable Long id) {

        String RequestBook = userHistoryService.remove(id);
        System.out.println(RequestBook);
        System.out.println(id   );
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RequestBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
//    @PutMapping("/decline/{id}")
//    public ResponseEntity<APIResponse> decline(@PathVariable Long id) {
//
//        String RequestBook = notificationService.decline(id);
//        System.out.println(RequestBook);
//        System.out.println(id   );
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(RequestBook);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
}
