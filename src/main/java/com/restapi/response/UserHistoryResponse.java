package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UserHistoryResponse {
        private Long id;
        private String name;
        private String book;
        private String issuedDate;
        private String returnDate;
        private boolean returned;
        private Long fineAmount;
        private Long BookId;
        private boolean renewal;



}
