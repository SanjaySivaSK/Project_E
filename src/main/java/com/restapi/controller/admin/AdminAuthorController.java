package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.request.AuthorRequest;
import com.restapi.response.AuthorResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/author")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminAuthorController {



    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AuthorService authorService;



    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllAuthor() {
        AuthorResponse authorResponse = authorService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(authorResponse.getAuthors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse> createAuthor(  @Valid @RequestBody
                                                        AuthorRequest authorRequest) {
        AuthorResponse authorResponse = authorService.create(authorRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(authorResponse.getAuthors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateAuthor(  @Valid @RequestBody
                                                            AuthorRequest authorRequest) {
        AuthorResponse authorResponse = authorService.update(authorRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(authorResponse.getAuthors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteAuthor(@PathVariable Integer id) {
        AuthorResponse authorResponse = authorService.deleteById(id);
        System.out.println(authorResponse);
        System.out.println(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(authorResponse.getAuthors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }}
