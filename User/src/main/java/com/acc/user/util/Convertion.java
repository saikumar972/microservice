package com.acc.user.util;

import com.acc.user.dto.UserRequest;
import com.acc.user.dto.UserResponse;
import com.acc.user.entity.Userentity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convertion {
    public static Userentity requestToEntity(UserRequest userRequest){
        return  Userentity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .paymentMethod(userRequest.getPaymentMethod().toUpperCase())
                .srcAccount(userRequest.getSrcAccount())
                .availableAmount(userRequest.getAvailableAmount())
                .build();
    }

    public static UserResponse entityToResponse(Userentity userentity){
        return  UserResponse.builder()
                .id(userentity.getId())
                .name(userentity.getName())
                .email(userentity.getEmail())
                .paymentMethod(userentity.getPaymentMethod())
                .srcAccount(userentity.getSrcAccount())
                .availableAmount(userentity.getAvailableAmount())
                .build();
    }
    public static String objectToJsonString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid input "+e);
        }
    }
}
