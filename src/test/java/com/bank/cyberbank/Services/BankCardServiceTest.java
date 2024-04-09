package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BankCardServiceTest {

    @InjectMocks
    private BankCardService controller;

    @Mock
    private BankCardRepository repository;
    private static final String STATUSCODE200_MESSAGE = "Successful";
    private  BankCard testModelBankCard;
    private BankCard testModelBankCardTwo;
    @BeforeEach
    void initModel() {
        testModelBankCard = new BankCard();{
            testModelBankCard.setNumberCard("4000 4000 4000 4000");
            testModelBankCard.setCardCVV("123");
            testModelBankCard.setNameOwnerCard("Test");
            testModelBankCard.setId(1);
            testModelBankCard.setLastNameOwnerCard("test");
            testModelBankCard.setBalance(1000);
        }
        testModelBankCardTwo = new BankCard();{
            testModelBankCardTwo.setNumberCard("4001 4000 4000 4000");
            testModelBankCardTwo.setCardCVV("123");
            testModelBankCardTwo.setNameOwnerCard("Test");
            testModelBankCardTwo.setId(1);
            testModelBankCardTwo.setLastNameOwnerCard("test");
            testModelBankCardTwo.setBalance(1000);
        }
    }

    @Test
    void createBankCardTest() {

        String nameOwnerCard = "TestName";
        String lastNameOwnerCard = "TestLastName";

        var result = controller.CreateBankCard(nameOwnerCard, lastNameOwnerCard);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
    }

    @Test
    void removeBankCardTest() {

        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(testModelBankCard);

        var result = controller.RemoveBankCard(numberBankCard);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
    }

    @Test
    void getAllCardTest() {

        Mockito.when(repository.AllCards()).thenReturn(List.of(testModelBankCard, testModelBankCardTwo));

        var result = controller.GetAllCard();

        Assertions.assertEquals(result, List.of(testModelBankCard, testModelBankCardTwo));
    }

    @Test
    void updateOwnerCardTest() {
        BankCardDTO testModelBankCard = new BankCardDTO();
        {
            testModelBankCard.setId(1);
            testModelBankCard.setNameOwnerCard("test");
            testModelBankCard.setLastNameOwnerCard("test");
            testModelBankCard.setExpirationDate("2/30");
        }

        var result = controller.UpdateOwnerCard(testModelBankCard);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
    }
}

