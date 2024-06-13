package com.bank.cyberbank.Services;

import org.springframework.stereotype.Service;


/**
 * Service for authorization and registration users
 */
@Service
public class AuthService {

    private static final String STATUSCODE200_MESSAGE = "Successful";


    //Todo create checking get name or last name for creating cards
    // + writing system db debug? when you can start app without originaly db


    public String register(String login, String password, String confirmPassword){

        return STATUSCODE200_MESSAGE;
    }

    public String login(String login, String password){

        return STATUSCODE200_MESSAGE;
    }
    public String logout(){

        return STATUSCODE200_MESSAGE;
    }
}
