package com.restapi.controller.admin;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.UserRepository;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/users")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminUserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userservice;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> findusers() {
        List<AppUser> users = userservice.findusers();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(users);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserDetail(@PathVariable Long userId) {
        Optional<AppUser> userBookList = userservice.findById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userBookList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }
}