package com.bank.cyberbank.Services;

import com.bank.cyberbank.Repositories.BankCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    @InjectMocks
    private BankService controller;

    @Mock
    private BankCardRepository repository;

    @Test
    void loadOwnMoneyTest(){

    }

}
