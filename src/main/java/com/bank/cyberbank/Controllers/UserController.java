package com.bank.cyberbank.Controllers;


import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Services.BankCardService;
import com.bank.cyberbank.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*

This class for end point for user

 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final BankService bankService;
    private final BankCardService bankCardService;

    @Autowired
    public UserController(BankService bankService, BankCardService bankCardService) {
        this.bankService = bankService;
        this.bankCardService = bankCardService;
    }


    //This method is used to create bank card
    @PostMapping("/createBankCard")
    public ResponseEntity<String> createBankCard(@RequestParam String nameOwnerCard, @RequestParam String lastNameOwnerCard) {
        if (nameOwnerCard != null && lastNameOwnerCard != null) {

            //This method  create bank card in database
            var result = bankCardService.CreateBankCard(nameOwnerCard, lastNameOwnerCard);
            if (result.equals("Successful")) {
                return ResponseEntity.ok().body("Create bank card - done");
            }
            return ResponseEntity.badRequest().body("Bank card not created");

        } else {
            return ResponseEntity.badRequest().body("nameOwnerCard or lastNameOwnerCard is null");
        }
    }

    @GetMapping("/bankCardInfo")
    public ResponseEntity<BankCard> informationOfBankCard(@RequestParam String nameOwnerCard) {
        if (nameOwnerCard != null) {
            var bankCard = bankService.bankCardInfo(nameOwnerCard);
            return (bankCard != null) ? ResponseEntity.ok(bankCard) : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/loadMoney")
    public ResponseEntity<String> loadMoneyToBankCard(
            @RequestParam String nameOwnerCard, @RequestParam int money
    ) {
        if (nameOwnerCard != null) {
            var resulr = bankService.LoadOwnMoney(nameOwnerCard, money);
            if (resulr.equals("Successful")) {
                return ResponseEntity.ok().body("Money loaded");
            }
            return ResponseEntity.badRequest().body("Error with operation");
        } else {
            return ResponseEntity.badRequest().body("Error: Invalid bank card name");
        }
    }

    @PostMapping("/withdrawMoney")
    public ResponseEntity<String> withdrawMoneyFromCard(
            @RequestParam String nameOwnerCard, @RequestParam int money
    ) {
        if (nameOwnerCard != null) {
            var result = bankService.WithdrawMoneyFromCard(nameOwnerCard, money);
            if (result.equals("Successful")) {
                return ResponseEntity.ok().body("Money withdraw");
            }
            return ResponseEntity.badRequest().body("Error with operation");
        } else {
            return ResponseEntity.badRequest().body("Error: Invalid bank card name");
        }
    }

    @PostMapping("/loadMoneyToAtherBankCard")
    public ResponseEntity<String> loadMoneyToAtherBankCard(@RequestParam String nameOwnerCard, @RequestParam String atherNameCard, int money) {
        if (nameOwnerCard != null && atherNameCard != null) {
            var result = bankService.loadOwnMoneyInOtherCard(atherNameCard, nameOwnerCard, money);
            if (result.equals("Successful")) {
                return ResponseEntity.ok().body("Money loaded at other card");
            } else if (result.equals("Bank cards not found")) {
                return ResponseEntity.badRequest().body("Error: Bank cards not found");
            }
            return ResponseEntity.badRequest().body("Error with operation");
        } else {
            return ResponseEntity.badRequest().body("Error: Invalid bank card name");
        }
    }

    /**
     * This method created for test api
     * @return text - hello
     */
    @GetMapping("/hello")
    public ResponseEntity<String> welcomeMassage(){
        return ResponseEntity.ok("Hello");
    }
}
