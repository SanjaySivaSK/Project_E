package com.restapi.controller.user;

import com.restapi.model.Book;
import com.restapi.model.Role;
import com.restapi.model.UserHistory;
import com.restapi.response.UserHistoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BookService;
import com.restapi.service.UserHistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-book")
@RolesAllowed(Role.USER)
public class UserHistoryController {
    @Autowired
    private APIResponse apiResponse;

     @Autowired
    private UserHistoryService userHistoryService;
    @GetMapping("/{userId}")
   public ResponseEntity<APIResponse> getUserBook(@PathVariable Long userId) {
       List<UserHistoryResponse> userBookList = userHistoryService.findById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setData(userBookList);
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);

   }
   @PutMapping("/{id}/update/{amt}/Book/{BookId}")
    public ResponseEntity<APIResponse>returnBooks(@PathVariable Long id,
                                                  @PathVariable Long amt,
                                                   @PathVariable Long BookId){
        String rBOOK= userHistoryService.returnBooks(id,amt,BookId);
       System.out.println(amt);
       System.out.println(id);
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setData(rBOOK);
       return new ResponseEntity<>(apiResponse, HttpStatus.OK);
   }
    @PutMapping("/Renewal/{id}")
    public ResponseEntity<APIResponse> Renewal(@PathVariable Long id) {
        System.out.println(id+"dddd");
        String RenewalBook = userHistoryService.Renewal(id);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(RenewalBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
