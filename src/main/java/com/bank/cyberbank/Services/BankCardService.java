package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class BankCardService
{
    private final BankCardRepository bankCardRepository;
    @Autowired
    public BankCardService(BankCardRepository bankCardRepository)
    {
        this.bankCardRepository = bankCardRepository;
    }
    //Method for creating bankcard for user
    public void CreateBankCard(String NameOwnerCard, String LastNameOwnerCard)
    {
        try{
            BankCard bank_card = new BankCard();

            bank_card.setNameOwnerCard(NameOwnerCard);
            bank_card.setLastNameOwnerCard(LastNameOwnerCard);
            bank_card.setBalance(0);
            bank_card.setExpirationDate(ExpirationDateCard());



            bankCardRepository.Add(bank_card);
        }
        catch (Exception ex)
        {

        }
    }
    public void RemoveBankCard()
    {

    }

    //We get ExpirationDate for bankcard
    public String ExpirationDateCard()
    {
        int current_year = (LocalDate.now().getYear())-2000;//get the last two year numbers
        int current_month = LocalDate.now().getMonthValue();//get the month number

        //return data on format 1/26 where 1 - month, 26 - year
        return current_month + "/" + (current_year+6);
    }
    public String CreateCvvCode()
    {
        Random random = new Random();
        r
        return null;

    }

}
