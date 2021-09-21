package com.llwstu.jwttokendemo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getPassword(){

        return "admin";
    }

}
