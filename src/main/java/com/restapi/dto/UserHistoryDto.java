package com.restapi.dto;

import com.restapi.model.UserHistory;
import com.restapi.request.UserHistoryRequest;
import com.restapi.response.UserHistoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHistoryDto {


    public static UserHistory mapToBook(UserHistoryRequest userHistoryRequest) {
        UserHistory userHistory =new UserHistory();
        if(userHistoryRequest.getId()!=null){
            userHistory.setId(userHistoryRequest.getId());




        }
         userHistory.setIssuedDate(userHistoryRequest.getIssuedDate());

        userHistory.setReturnDate(userHistoryRequest.getReturnDate());
        userHistory.setReturned(false);
        userHistory.setRenewal(false);


         return userHistory;


    }


    public static List<UserHistoryResponse>mapToUserResponse(List<UserHistory> userHistoryList) {
       List<UserHistoryResponse>  userHistoryResponseList=new ArrayList<>();

        for(UserHistory userHistory:userHistoryList){
            UserHistoryResponse userHistoryResponse=new UserHistoryResponse();
            System.out.println(userHistory.getAppUser().getName());
            userHistoryResponse.setName(userHistory.getAppUser().getName());
            userHistoryResponse.setBook(userHistory.getBook().getBook());
            userHistoryResponse.setIssuedDate(userHistory.getIssuedDate());
            userHistoryResponse.setReturnDate(userHistory.getReturnDate());
            userHistoryResponse.setId(userHistory.getId());
            userHistoryResponse.setReturned(userHistory.isReturned());
            userHistoryResponse.setRenewal(userHistory.isRenewal());
            userHistoryResponse.setFineAmount(userHistory.getFineAmount());
            userHistoryResponse.setBookId(userHistory.getBook().getId());
            userHistoryResponseList.add(userHistoryResponse);


        }

        return userHistoryResponseList;
    }
}

