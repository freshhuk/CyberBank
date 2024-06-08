package com.bank.cyberbank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * End point for aoth methods
 * Login, register, logout
 */
@RestController
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String login, @RequestParam String password){

        return ResponseEntity.ok().body("Register Successful");
    }
    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password){

        return ResponseEntity.ok().body("Login  Successful");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(){

        return ResponseEntity.ok().body("Logout  Successful");
    }
}
