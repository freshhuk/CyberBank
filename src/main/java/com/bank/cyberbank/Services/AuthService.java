package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Enums.Role;
import com.bank.cyberbank.Domain.Models.UserDTO;
import com.bank.cyberbank.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String register(String login, String password, String confirmPassword){
        try{
            if (password.equals(confirmPassword) && isValidLogin(login)){
                User user = new User();
                {

                    user.setLogin(login);
                    user.setPassword(passwordEncoder.encode(password));
                    if(login.equals("Admin"))
                        user.setRole(Role.ADMIN);
                    else
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
    public List<User> getUsers(){
        try {
            return repository.getAllUsers();
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return null;
        }
    }
    public String deleteUser(int id){
        try{
            repository.deleteUser(id);
            return STATUSCODE200_MESSAGE;
        }
        catch(Exception ex){
            System.out.println("Error with operation " + ex);
            return "Error";
        }
    }
    public String updateUser(UserDTO userDTO){
        try{
            if(userDTO != null){
                repository.updateUser(userDTO);
                return STATUSCODE200_MESSAGE;
            }
            else {
                return "Model is null";
            }
        } catch (Exception ex){
            System.out.println("Error with operation + " + ex);
            return "Error";
        }
    }

    private boolean isValidLogin(String login){
        var user = repository.getUserByLogin(login);
        return user.isEmpty();
    }
}
