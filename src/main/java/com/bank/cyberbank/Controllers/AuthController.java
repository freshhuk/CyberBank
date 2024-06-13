package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * End point for aoth methods
 * Login, register, logout
 */
@RestController
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String login, @RequestParam String password, @RequestParam String confirmPassword){

        String result = authService.register(login, password, confirmPassword);
        if(result.equals("Successful")){
            return ResponseEntity.ok().body("Register Successful");
        }
        else{
            return ResponseEntity.badRequest().body("Register error");
        }
    }
    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password){

        String result = authService.login(login, password);
        if(result.equals("Successful")){
            return ResponseEntity.ok().body("Login Successful");
        }
        else{
            return ResponseEntity.badRequest().body("Login error");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(){

        String result = authService.logout();
        if(result.equals("Successful")){
            return ResponseEntity.ok().body("Logout Successful");
        }
        else{
            return ResponseEntity.badRequest().body("Logout error");
        }
    }
    /**
     * This method created for test api
     * @return text - hello
     */
    @GetMapping("/hello")
    public ResponseEntity<String> welcomeMassage(){
        return ResponseEntity.ok("Hello");
    }
}
