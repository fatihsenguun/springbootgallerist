package com.fatihsengun.service.impl;

import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.model.Gallerist;
import com.fatihsengun.model.User;
import com.fatihsengun.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityService {

    @Autowired
    private AuthRepository authRepository;

    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByUsername(username).orElseThrow(()->new BaseException(
                new ErrorMessage(MessageType.USER_NOT_FOUND,username)));
    }

    public Gallerist getCurrentGallerist(){
        User user = getCurrentUser();
        if (user.getGallerist()==null){
            throw new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND,"gallerist"));

        }return user.getGallerist();
    }




}
