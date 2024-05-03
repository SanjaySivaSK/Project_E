package com.restapi.controller.user;

import com.restapi.model.Book;
import com.restapi.model.Role;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user/book")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class UserBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllBooks() {
        List<Book> bookList = bookService.FindAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


//    @GetMapping("/downloadFile/{id}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
//        File file = bookService.getFile(id);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }
//

}
