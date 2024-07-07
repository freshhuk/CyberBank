package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Domain.Models.UserDTO;
import com.bank.cyberbank.Services.AuthService;
import com.bank.cyberbank.Services.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
This class for end point for user
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin")
public class AdminController {

    private final BankCardService bankCardService;
    private final AuthService authService;

    @Autowired
    public AdminController(BankCardService bankCardService, AuthService authService) {
        this.bankCardService = bankCardService;
        this.authService = authService;
    }

    @GetMapping("/getAllCards")
    public ResponseEntity<List<BankCard>> checkAllCardInfo() {
        try {
            List<BankCard> cards = bankCardService.GetAllCard();
            return ResponseEntity.ok().body(cards);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deleteUserCard")
    public ResponseEntity<String> deleteUserCard(@RequestParam String bankCardNumber) {
        String result = bankCardService.RemoveBankCard(bankCardNumber);

        if (result.equals("Successful")) {
            return ResponseEntity.ok().body("User deleted");
        } else if (result.equals("BankCard_Number is null")) {
            return ResponseEntity.badRequest().body("User card not found");
        }
        return ResponseEntity.badRequest().body("Error");
    }

    @PutMapping("/changeDataCard")
    public ResponseEntity<String> changeDataCard(@RequestBody BankCardDTO bankCard) {
        String result = bankCardService.UpdateOwnerCard(bankCard);
        if (result.equals("Successful")) {
            return ResponseEntity.ok().body("Card data was changed");
        } else if (result.equals("Model is null")) {
            return ResponseEntity.badRequest().body("Bank card DTO is null");
        }
        return ResponseEntity.badRequest().body("Error");
    }
    //Action with user
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> cards = authService.getUsers();

            return ResponseEntity.ok().body(cards);
        } catch (Exception ex) {
            System.out.println("Error with get user");
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable  int id){
        String result = authService.deleteUser(id);
        return result.equals("Successful") ? ResponseEntity.ok().body("User deleted") : ResponseEntity.badRequest().body("Error, user didn't delete");
    }
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userModel){
        String result = authService.updateUser(userModel);
        if (result.equals("Successful")) {
            return ResponseEntity.ok().body("User data was changed");
        } else if (result.equals("Model is null")) {
            return ResponseEntity.badRequest().body("User DTO is null");
        }
        return ResponseEntity.badRequest().body("Error");
    }
}
