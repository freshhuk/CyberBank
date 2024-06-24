package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Enums.Role;
import com.bank.cyberbank.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Service for authorization and registration users
 */
@Service
public class AuthService {

    private static final String STATUSCODE200_MESSAGE = "Successful";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public  AuthService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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

        return STATUSCODE200_MESSAGE;
    }
    public String logout(){

        return STATUSCODE200_MESSAGE;
    }
}
