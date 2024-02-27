package com.bank.cyberbank.Controllers;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Services.BankCardService;
import com.bank.cyberbank.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*

This class for end point for user

 */
@RestController
@RequestMapping("/api/admin")
public class AdminController
{
    private final BankService bankService;
    private final BankCardService bankCardService;

    @Autowired
    public AdminController(BankService bankService, BankCardService bankCardService)
    {
        this.bankService = bankService;
        this.bankCardService = bankCardService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<BankCard>> checkAllUserInfo()
    {
        try{
            List<BankCard> cards =  bankCardService.GetAllCard();
            return ResponseEntity.ok().body(cards);
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().body(null);
        }

    }
    public void deleteUser()
    {

    }
    public void changeDataUser()
    {

    }

}
