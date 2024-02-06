package com.bank.cyberbank.Controllers;


import com.bank.cyberbank.Services.BankCardService;
import com.bank.cyberbank.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*

This class for end point for user

 */
@RestController
@RequestMapping("/api/user")
public class UserController
{
    private final BankService bankService;
    private final BankCardService bankCardService;

    @Autowired
    public UserController(BankService bankService, BankCardService bankCardService)
    {
        this.bankService = bankService;
        this.bankCardService = bankCardService;
    }


    //This method is used to create bank card
    @PostMapping("/createBankCard")
    public ResponseEntity<String> createBankCard(@RequestParam String nameOwnerCard, @RequestParam String lastNameOwnerCard)
    {
        if(nameOwnerCard != null && lastNameOwnerCard != null) {

            //This method  create bank card in database
            var result = bankCardService.CreateBankCard(nameOwnerCard,lastNameOwnerCard);
            if(result.equals("Successful")){
                return ResponseEntity.ok().body("Create bank card - done");
            }
            return ResponseEntity.badRequest().body("Bank card not created");

        }
        else{
            return ResponseEntity.badRequest().body("nameOwnerCard or lastNameOwnerCard is null");
        }
    }
}
