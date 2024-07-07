package com.bank.cyberbank.Controllers;


import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Services.BankCardService;
import com.bank.cyberbank.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*

This class for end point for user

 */
@RestController
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
@RequestMapping("/api/user")
public class UserController {
    private final BankService bankService;
    private final BankCardService bankCardService;

    @Autowired
    public UserController(BankService bankService, BankCardService bankCardService) {
        this.bankService = bankService;
        this.bankCardService = bankCardService;
    }


    /**
     * The end point for creating a bank card.
     * Calls the CreateBankCard method which created a bank card model and entered its database
     *
     * @param nameOwnerCard Bank card owner's name
     * @param lastNameOwnerCard Bank card owner's surname
     *
     * @return Depending on the result, adding an object to the database is the same.
     * If everything goes well we get code 200, otherwise 400
     */
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

    /**
     * End point to find out information about a bank card.
     * About the owner, balance, card number, etc.
     * @param bankCardNumber card number by which the database will be searched
     * @return If we get the correct card number, the program will find it and return status 200 and the object itself,
     * if the card is not found or the number is written incorrectly, it will return error 400
     */
    @GetMapping("/bankCardInfo")
    public ResponseEntity<BankCard> informationOfBankCard(@RequestParam String bankCardNumber) {
        if (bankCardNumber != null) {
            var bankCard = bankService.bankCardInfo(bankCardNumber);
            return (bankCard != null) ? ResponseEntity.ok(bankCard) : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    /**
     * Endpoint for loading money onto a bank card
     * @param bankCardNumber Number of the card on which the money will be deposited
     * @param money the amount of money that will be deposited into the account
     * @return If we get the correct card number, the program will find it and return status 200 and the object itself,
     *       if the card is not found or the number is written incorrectly, it will return error 400
     */
    @PostMapping("/loadMoney")
    public ResponseEntity<String> loadMoneyToBankCard(
            @RequestParam String bankCardNumber, @RequestParam int money) {
        if (bankCardNumber != null) {
            var resulr = bankService.LoadOwnMoney(bankCardNumber, money);
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
            @RequestParam String bankCardNumber, @RequestParam int money
    ) {
        if (bankCardNumber != null) {
            var result = bankService.WithdrawMoneyFromCard(bankCardNumber, money);
            if (result.equals("Successful")) {
                return ResponseEntity.ok().body("Money withdraw");
            }
            return ResponseEntity.badRequest().body("Error with operation");
        } else {
            return ResponseEntity.badRequest().body("Error: Invalid bank card name");
        }
    }

    @PostMapping("/loadMoneyToAtherBankCard")
    public ResponseEntity<String> loadMoneyToOtherBankCard(@RequestParam String bankCardNumber, @RequestParam String atherNameCard, int money) {
        if (bankCardNumber != null && atherNameCard != null) {
            var result = bankService.loadOwnMoneyInOtherCard(atherNameCard, bankCardNumber, money);
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
    @GetMapping("/welcome")
    public String welcomeMessage(){
        return "Welcome , user!";
    }
}
