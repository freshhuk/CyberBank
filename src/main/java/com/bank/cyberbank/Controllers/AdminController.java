package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Services.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
This class for end point for user
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BankCardService bankCardService;

    @Autowired
    public AdminController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<BankCard>> checkAllUserInfo() {
        try {
            List<BankCard> cards = bankCardService.GetAllCard();
            return ResponseEntity.ok().body(cards);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @DeleteMapping("/deleteUserCard")
    public ResponseEntity<String> deleteUser(@RequestParam String bankCardNumber) {
        String result = bankCardService.RemoveBankCard(bankCardNumber);

        if (result.equals("Successful")) {
            return ResponseEntity.ok().body("User deleted");
        } else if (result.equals("BankCard_Number is null")) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.badRequest().body("Error");
    }

    @PutMapping("/changeDataUser")
    public ResponseEntity<String> changeDataUser(@RequestBody BankCardDTO bankCard) {
        String result = bankCardService.UpdateOwnerCard(bankCard);
        if (result.equals("Successful")) {
            return ResponseEntity.ok().body("User data was changed");
        } else if (result.equals("Model is null")) {
            return ResponseEntity.badRequest().body("Bank card DTO is null");
        }
        return ResponseEntity.badRequest().body("Error");
    }

}
