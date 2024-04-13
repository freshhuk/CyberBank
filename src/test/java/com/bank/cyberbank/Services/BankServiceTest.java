package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    @InjectMocks
    private BankService controller;

    @Mock
    private BankCardRepository repository;
    private static final String STATUSCODE200_MESSAGE = "Successful";
    private static final String STATUSCODE404_MESSAGE = "Bank card not found";
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



    //Test when we have correct bankCard
    @Test
    void loadOwnMoneyTest(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(new BankCard());
        var result = controller.LoadOwnMoney(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);

    }
    @Test
    void loadOwnMoneyTestWhenBankCardNotFound(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(null);
        var result = controller.LoadOwnMoney(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE404_MESSAGE);

    }
    //Test when we have correct bankCard
    @Test
    void withdrawMoneyFromCardTest(){
        String numberBankCard = "4000 4000 4000 4000";

        var testBankCard = new BankCard();
        testBankCard.setBalance(200);

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(testBankCard);
        var result = controller.WithdrawMoneyFromCard(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);

    }
    @Test
    void withdrawMoneyFromCardTestWhenBankCardNotFound(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(null);
        var result = controller.WithdrawMoneyFromCard(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE404_MESSAGE);

    }
    @Test
    void loadOwnMoneyInOtherCardTest(){
        String numberBankCard = "4000 4000 4000 4000";
        String otherNumberBankCard = "4001 4000 4000 4000";
        int loadMoney = 100;

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(testModelBankCard);
        Mockito.when(repository.GetBankCardByNumber(otherNumberBankCard)).thenReturn(testModelBankCardTwo);

        var result = controller.loadOwnMoneyInOtherCard(numberBankCard,otherNumberBankCard,loadMoney);

        Assertions.assertEquals(result, STATUSCODE200_MESSAGE);
        Assertions.assertEquals(testModelBankCard.getBalance(), 900);
        Assertions.assertEquals(testModelBankCardTwo.getBalance(), 1100);
    }
    @Test
    void bankCardInfoTest(){
        String numberBankCard = "4000 4000 4000 4000";
        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(testModelBankCard);

        var result = controller.bankCardInfo(numberBankCard);

        Assertions.assertEquals(result, testModelBankCard);
    }
    @Test
    void bankCardInfoWhenReturnNullTest(){
        String numberBankCard = "4000 4000 4000 4000";
        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(null);

        var result = controller.bankCardInfo(numberBankCard);

        Assertions.assertNull(result);
    }
}
