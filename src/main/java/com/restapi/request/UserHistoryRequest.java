package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UserHistoryRequest {

    private Long Id;
    private Long userId;
    private Long BookId;

    private String issuedDate;
    private String returnDate;
    private Long fineAmount;
    private boolean decline;




}
