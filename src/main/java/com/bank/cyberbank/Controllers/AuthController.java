package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Collection;

/**
 * End point for aoth methods
 * Login, register, logout
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String login, @RequestParam String password, @RequestParam String confirmPassword){

        String result = authService.register(login, password, confirmPassword);
        return switch (result) {
            case "Successful" -> ResponseEntity.ok().body("Register Successful");
            case "Error" -> ResponseEntity.badRequest().body("Error with opertion");
            case "Model is null" -> ResponseEntity.badRequest().body("Model is null");
            default -> ResponseEntity.badRequest().body("Register error");
        };
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
    @GetMapping("/checkRoles")
    public String checkRoles(Authentication authentication) {
        if (authentication == null) {
            return "Not authenticated";
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return "Roles: " + authorities.toString();
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
