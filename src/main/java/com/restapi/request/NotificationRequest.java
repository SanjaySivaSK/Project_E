package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NotificationRequest {
    private Long id;
    private Long userId;
    private Long bookId;
    private boolean request;
    private boolean renewal;

}
