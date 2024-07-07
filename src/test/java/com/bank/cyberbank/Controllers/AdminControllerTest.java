package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Enums.Role;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Domain.Models.UserDTO;
import com.bank.cyberbank.Services.AuthService;
import com.bank.cyberbank.Services.BankCardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class AdminControllerTest {

    @InjectMocks
    private  AdminController controller;
    @Mock
    private BankCardService bankCardService;
    @Mock
    private AuthService authService;

    private  BankCard testModelBankCard;
    private BankCard testModelBankCardTwo;
    private BankCardDTO cardDTO;

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
        cardDTO = new BankCardDTO();{
            cardDTO.setId(1);
            cardDTO.setNameOwnerCard("Test");
            cardDTO.setLastNameOwnerCard("Testovich");
            cardDTO.setExpirationDate("1/26");
        }
    }
    @Test
    void checkAllCardInfoTest(){

        Mockito.when(bankCardService.GetAllCard()).thenReturn(List.of(testModelBankCard, testModelBankCardTwo));
        ResponseEntity<List<BankCard>> result = controller.checkAllCardInfo();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBody(), List.of(testModelBankCard, testModelBankCardTwo));

    }
    @Test
    void deleteUserCardTest(){

        Mockito.when(bankCardService.RemoveBankCard("123")).thenReturn("Successful");

        ResponseEntity<String> result = controller.deleteUserCard("123");

        Assertions.assertEquals(result.getBody(), "User deleted");
    }
    @Test
    void deleteUserCardWhenCardNullTest(){

        Mockito.when(bankCardService.RemoveBankCard(null)).thenReturn("BankCard_Number is null");

        ResponseEntity<String> result = controller.deleteUserCard(null);

        Assertions.assertEquals(result.getBody(), "User card not found");
    }
    @Test
    void deleteUserCardWhenErrorTest(){

        Mockito.when(bankCardService.RemoveBankCard("123")).thenReturn("Excaption");

        ResponseEntity<String> result = controller.deleteUserCard("123");

        Assertions.assertEquals(result.getBody(), "Error");
    }

    @Test
    void changeDataCardTest(){

        Mockito.when(bankCardService.UpdateOwnerCard(cardDTO)).thenReturn("Successful");

        ResponseEntity<String> result = controller.changeDataCard(cardDTO);

        Assertions.assertEquals(result.getBody(), "Card data was changed");
    }
    @Test
    void changeDataCardNullModelTest(){

        Mockito.when(bankCardService.UpdateOwnerCard(null)).thenReturn("Model is null");

        ResponseEntity<String> result = controller.changeDataCard(null);

        Assertions.assertEquals(result.getBody(), "Bank card DTO is null");
    }
    @Test
    void changeDataCardWhenErrorTest(){

        Mockito.when(bankCardService.UpdateOwnerCard(cardDTO)).thenReturn("Error");

        ResponseEntity<String> result = controller.changeDataCard(cardDTO);

        Assertions.assertEquals(result.getBody(), "Error");
    }

    @Test
    void getAllUsersTest(){

        User user1 = new User();{
            user1.setLogin("123");
            user1.setPassword("123");
            user1.setId(1);
            user1.setRole(Role.USER);
        }
        User user2 = new User();{
            user2.setLogin("Test");
            user2.setPassword("Test");
            user2.setId(2);
            user2.setRole(Role.USER);
        }

        Mockito.when(authService.getUsers()).thenReturn(List.of(user1, user2));

        ResponseEntity<List<User>> result = controller.getAllUsers();

        Assertions.assertEquals(result.getBody(), List.of(user1, user2));
        Assertions.assertNotNull(result);
    }
    @Test
    void getAllUsersExceptionTest(){


        Mockito.when(authService.getUsers()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<User>> result = controller.getAllUsers();

        Assertions.assertNull(result.getBody());
    }

    @Test
    void deleteUserTest(){

        Mockito.when(authService.deleteUser(1)).thenReturn("Successful");
        ResponseEntity<String> result = controller.deleteUser(1);

        Assertions.assertEquals(result.getBody(), "User deleted");

    }
    @Test
    void deleteUserErrorTest(){

        Mockito.when(authService.deleteUser(1)).thenReturn("Error");
        ResponseEntity<String> result = controller.deleteUser(1);

        Assertions.assertEquals(result.getBody(), "Error, user didn't delete");

    }

    @Test
    void updateUserTest(){

        UserDTO user = new UserDTO();{
            user.setId(1);
            user.setNew_login("123");
            user.setNew_password("123");
        }
        Mockito.when(authService.updateUser(user)).thenReturn("Successful");

        ResponseEntity<String> result = controller.updateUser(user);

        Assertions.assertEquals(result.getBody(), "User data was changed");

    }
    @Test
    void updateUserModelNullTest(){

        Mockito.when(authService.updateUser(null)).thenReturn("Model is null");

        ResponseEntity<String> result = controller.updateUser(null);

        Assertions.assertEquals(result.getBody(), "User DTO is null");

    }
    @Test
    void updateUserWhenErrorTest(){

        UserDTO user = new UserDTO();{
            user.setId(1);
            user.setNew_login("123");
            user.setNew_password("123");
        }
        Mockito.when(authService.updateUser(user)).thenReturn("Error");

        ResponseEntity<String> result = controller.updateUser(user);

        Assertions.assertEquals(result.getBody(), "Error");

    }
}
