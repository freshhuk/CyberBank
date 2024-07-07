package com.bank.cyberbank.Controllers;


import com.bank.cyberbank.Services.BankCardService;
import com.bank.cyberbank.Services.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private BankService bankService;
    @Mock
    private BankCardService bankCardService;

    @Test
    void createBankCardTest(){

        Mockito.when(bankCardService.CreateBankCard("Test", "Test2")).thenReturn("Successful");
        ResponseEntity<String> result = controller.createBankCard("Test", "Test2");

        Assertions.assertEquals(result.getBody(), "Create bank card - done");

    }
    @Test
    void createBankCardErrorWithOperationTest(){

        Mockito.when(bankCardService.CreateBankCard("Test", "Test2")).thenReturn("Error");
        ResponseEntity<String> result = controller.createBankCard("Test", "Test2");

        Assertions.assertEquals(result.getBody(), "Bank card not created");

    }
    @Test
    void createBankCardNullFieldTest(){

        ResponseEntity<String> result = controller.createBankCard("Test", null);

        Assertions.assertEquals(result.getBody(), "nameOwnerCard or lastNameOwnerCard is null");
    }

    @Test
    void loadMoneyToBankCardTest(){

        Mockito.when(bankService.LoadOwnMoney("4444", 100)).thenReturn("Successful");

        ResponseEntity<String> result = controller.loadMoneyToBankCard("4444", 100);

        Assertions.assertEquals(result.getBody(), "Money loaded");
    }
    @Test
    void loadMoneyToBankCardErrorOperationTest(){

        Mockito.when(bankService.LoadOwnMoney("4444", 100)).thenReturn("Error");

        ResponseEntity<String> result = controller.loadMoneyToBankCard("4444", 100);

        Assertions.assertEquals(result.getBody(), "Error with operation");
    }
    @Test
    void loadMoneyToBankCardNullCardTest(){

        ResponseEntity<String> result = controller.loadMoneyToBankCard(null, 100);

        Assertions.assertEquals(result.getBody(), "Error: Invalid bank card name");
    }
    @Test
    void withdrawMoneyFromCardTest(){

        Mockito.when(bankService.WithdrawMoneyFromCard("4444", 100)).thenReturn("Successful");

        ResponseEntity<String> result = controller.withdrawMoneyFromCard("4444", 100);

        Assertions.assertEquals(result.getBody(), "Money withdraw");
    }
    @Test
    void withdrawMoneyFromCardErrorOperationTest(){

        Mockito.when(bankService.WithdrawMoneyFromCard("4444", 100)).thenReturn("Error");

        ResponseEntity<String> result = controller.withdrawMoneyFromCard("4444", 100);

        Assertions.assertEquals(result.getBody(), "Error with operation");
    }
    @Test
    void withdrawMoneyFromCardNullCardTest(){

        ResponseEntity<String> result = controller.withdrawMoneyFromCard(null, 100);

        Assertions.assertEquals(result.getBody(), "Error: Invalid bank card name");
    }

    @Test
    void loadMoneyToOtherBankCardTest(){

        Mockito.when(bankService.loadOwnMoneyInOtherCard("4444", "5555", 10)).thenReturn("Successful");
        ResponseEntity<String> result = controller.loadMoneyToOtherBankCard("5555", "4444", 10);

        Assertions.assertEquals(result.getBody(), "Money loaded at other card");
    }
    @Test
    void loadMoneyToOtherBankCardNotFoundTest(){

        Mockito.when(bankService.loadOwnMoneyInOtherCard("4444", "5555", 10)).thenReturn("Bank cards not found");
        ResponseEntity<String> result = controller.loadMoneyToOtherBankCard("5555", "4444", 10);

        Assertions.assertEquals(result.getBody(), "Error: Bank cards not found");
    }
    @Test
    void loadMoneyToOtherBankCardErrorTest(){

        Mockito.when(bankService.loadOwnMoneyInOtherCard("4444", "5555", 10)).thenReturn("Error");
        ResponseEntity<String> result = controller.loadMoneyToOtherBankCard("5555", "4444", 10);

        Assertions.assertEquals(result.getBody(), "Error with operation");
    }

    @Test
    void loadMoneyToOtherBankCardNullCardsTest(){

        ResponseEntity<String> result = controller.loadMoneyToOtherBankCard(null, "4444", 10);

        Assertions.assertEquals(result.getBody(), "Error: Invalid bank card name");
    }
    @Test
    void welcomeMessageTest(){
        String result = controller.welcomeMessage();
        Assertions.assertEquals(result, "Welcome , user!");
    }
}
