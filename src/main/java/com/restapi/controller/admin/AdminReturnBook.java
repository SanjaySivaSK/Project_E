package com.restapi.controller.admin;


import com.restapi.model.Role;
import com.restapi.model.UserHistory;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/return")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminReturnBook {
    @Autowired
    private UserHistoryService userHistoryService;
    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/returnedBook")
    public ResponseEntity<APIResponse> getReturnedBook() {
        List<UserHistory> returnBook = userHistoryService.returnedBook();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(returnBook);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
