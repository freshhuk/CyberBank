package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.junit.jupiter.api.Assertions;
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
    private static final String STATUSCODE200_MASSAGE = "Successful";
    private static final String STATUSCODE404_MASSAGE = "Bank card not found";


    //Test when we have correct bankCard
    @Test
    void loadOwnMoneyTest(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(new BankCard());
        var result = controller.LoadOwnMoney(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE200_MASSAGE);

    }
    @Test
    void loadOwnMoneyTestWhenBankCardNotFound(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(null);
        var result = controller.LoadOwnMoney(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE404_MASSAGE);

    }
    //Test when we have correct bankCard
    @Test
    void withdrawMoneyFromCardTest(){
        String numberBankCard = "4000 4000 4000 4000";

        var testBankCard = new BankCard();
        testBankCard.setBalance(200);

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(testBankCard);
        var result = controller.WithdrawMoneyFromCard(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE200_MASSAGE);

    }
    @Test
    void withdrawMoneyFromCardTestWhenBankCardNotFound(){
        String numberBankCard = "4000 4000 4000 4000";

        Mockito.when(repository.GetBankCardByNumber(numberBankCard)).thenReturn(null);
        var result = controller.WithdrawMoneyFromCard(numberBankCard,10);

        Assertions.assertEquals(result, STATUSCODE404_MASSAGE);

    }
}
