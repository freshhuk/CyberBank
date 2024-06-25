package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Enums.Role;
import com.bank.cyberbank.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;


/**
 * Service for authorization and registration users
 */
@Service
public class AuthService {

    private static final String STATUSCODE200_MESSAGE = "Successful";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public  AuthService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    //Todo create checking get name or last name for creating cards


    public String register(String login, String password, String confirmPassword){
        try{
            if (password.equals(confirmPassword)){
                User user = new User();
                {
                    user.setLogin(login);
                    user.setPassword(passwordEncoder.encode(password));
                    user.setRole(Role.USER);
                }
                String result = repository.addUser(user);
                switch (result) {
                    case STATUSCODE200_MESSAGE -> {
                        return STATUSCODE200_MESSAGE;
                    }
                    case "Error" -> {
                        return "Error with opertion";
                    }
                    default ->  {
                        return "Model is null";
                    }
                }
            }
            else{
                return "Password dont confirm";
            }
        }
        catch (Exception ex){
            System.out.println("Error exeption " + ex);
            return "Error with register";
        }
    }

    public String login(String login, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return STATUSCODE200_MESSAGE;
        } catch (AuthenticationException e) {
            System.out.println("Login failed: " + e.getMessage());
            return "Error";
        }
    }
    public String logout(){

        return STATUSCODE200_MESSAGE;
    }
}
