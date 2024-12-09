package com.acc.user.service;

import com.acc.user.customAnnotarion.Userlog;
import com.acc.user.customExceptions.InvalidAmountException;
import com.acc.user.customExceptions.InvalidIdException;
import com.acc.user.dto.UserRequest;
import com.acc.user.dto.UserResponse;
import com.acc.user.entity.Userentity;
import com.acc.user.repository.UserRepo;
import com.acc.user.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Userlog
public class UserService {
    @Autowired
    UserRepo repo;

    public UserResponse adduser( UserRequest userRequest){
        Userentity userentity= Convertion.requestToEntity(userRequest);
         repo.save(userentity);
        UserResponse userResponse=Convertion.entityToResponse(userentity);
        return userResponse;
    }

    public UserResponse getUserById(int id){
        Userentity userentity=repo.findById(id).orElseThrow(()->new InvalidIdException("Invalid id"));
        UserResponse userResponse=Convertion.entityToResponse(userentity);
        return userResponse;
    }

    public UserResponse updateAmount(int id,double amount){
        Userentity userentity=repo.findById(id).orElseThrow(()->new InvalidIdException("Invalid id"));
        if(amount>userentity.getAvailableAmount()){
            throw new InvalidAmountException("Insufficient funds");
        }
        userentity.setAvailableAmount(userentity.getAvailableAmount()-amount);
        repo.save(userentity);
        UserResponse userResponse=Convertion.entityToResponse(userentity);
        return userResponse;
    }

    public List<UserResponse> getAllusers(){
        List<Userentity> userList=repo.findAll();
        List<UserResponse> responseList = new ArrayList<>();
        userList.forEach((users)->responseList.add(Convertion.entityToResponse(users)));
        return responseList;
    }
}
