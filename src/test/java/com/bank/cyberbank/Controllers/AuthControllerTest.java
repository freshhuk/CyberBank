package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Services.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController controller;
    @Mock
    private AuthService authService;

    @Test
    void registerTest(){
        Mockito.when(authService.register("test", "123", "123")).thenReturn("Successful");

        ResponseEntity<String> result = controller.register("test", "123", "123");

        Assertions.assertEquals(result.getBody(), "Register Successful");
    }
    @Test
    void registerErrorWithOperationTest(){
        Mockito.when(authService.register("test", "123", "123")).thenReturn("Error");

        ResponseEntity<String> result = controller.register("test", "123", "123");

        Assertions.assertEquals(result.getBody(), "Error with opertion");
    }
    @Test
    void registerNullTest(){
        Mockito.when(authService.register(null, "123", "123")).thenReturn("Model is null");

        ResponseEntity<String> result = controller.register(null, "123", "123");

        Assertions.assertEquals(result.getBody(), "Model is null");
    }
    @Test
    void registerErrorTest(){
        Mockito.when(authService.register(null, "123", "123")).thenReturn("Error !");

        ResponseEntity<String> result = controller.register(null, "123", "123");

        Assertions.assertEquals(result.getBody(), "Register error");
    }
    @Test
    void checkRolesTest(){

        String result = controller.checkRoles(null);
        Assertions.assertEquals(result, "Not authenticated");
    }
    @Test
    void welcomeMassageTest(){
        ResponseEntity<String> result = controller.welcomeMassage();
        Assertions.assertEquals(result.getBody(), "Hello");

    }
}
