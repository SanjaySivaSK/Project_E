package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
   @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "Username must start with a capital letter")
    private String username;

    @NotEmpty
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;
}
