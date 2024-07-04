package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Models.UserDTO;
import com.bank.cyberbank.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)

public class AuthServiceTest {

    @InjectMocks
    private AuthService service;

    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private static final String STATUSCODE200_MESSAGE = "Successful";

    @Test
    void registerTest(){
        String login = "Test";
        String password = "123";
        String encodedPassword = "encoded123";

        Mockito.when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        Mockito.when(repository.addUser(Mockito.any(User.class))).thenReturn(STATUSCODE200_MESSAGE);

        String result = service.register(login, password, password);

        Assertions.assertEquals(STATUSCODE200_MESSAGE, result);
    }
    @Test
    void registerTestError(){
        String login = "Test";
        String password = "123";
        String encodedPassword = "encoded123";

        Mockito.when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        Mockito.when(repository.addUser(Mockito.any(User.class))).thenReturn("Error");

        String result = service.register(login, password, password);

        Assertions.assertEquals("Error with opertion", result);
    }
    @Test
    void registerTestNullModell(){
        String login = "Test";
        String password = "123";

        Mockito.when(repository.addUser(Mockito.any(User.class))).thenReturn("Model is null");

        String result = service.register(login, password, password);

        Assertions.assertEquals("Model is null", result);
    }
    @Test
    void registerTestPasswordNotConfirm(){
        String login = "Test";

        String password = "123";
        String passwordConfirm = "1234";

        String result = service.register(login, password, passwordConfirm);

        Assertions.assertEquals("Password dont confirm", result);
    }
    @Test
    void registerTest_ExceptionDuringRegistration() {
        String login = "Test";
        String password = "123";

        Mockito.when(passwordEncoder.encode(password)).thenThrow(new RuntimeException("Encryption error"));

        String result = service.register(login, password, password);

        Assertions.assertEquals("Error with register", result);
    }

    @Test
    void deleteTest(){

        String result = service.deleteUser(1);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);

    }

    @Test
    void updateUserTest(){

        UserDTO userTest = new UserDTO();
        userTest.setId(1);
        userTest.setNew_login("Test");
        userTest.setNew_password("123");

        String result = service.updateUser(userTest);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
    }
    @Test
    void updateUserWhenUserNullTest(){
        String result = service.updateUser(null);

        Assertions.assertEquals(result, "Model is null");
    }

    @Test
    void getUsersTest(){

    }
}
