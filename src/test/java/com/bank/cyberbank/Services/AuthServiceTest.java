package com.bank.cyberbank.Services;

import com.bank.cyberbank.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class AuthServiceTest {

    @InjectMocks
    private AuthService service;

    @Mock
    private UserRepository repository;
    private static final String STATUSCODE200_MESSAGE = "Successful";

    @Test
    void registerTest(){
        String login = "Test";
        String passsword = "123";
        String confirmPassword = "123";

        String result = service.register(login, passsword, confirmPassword);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
    }

}
