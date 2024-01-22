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
            {
                bank_card.setNameOwnerCard(NameOwnerCard);
                bank_card.setLastNameOwnerCard(LastNameOwnerCard);

                bank_card.setBalance(0);
                bank_card.setExpirationDate(ExpirationDateCard());
                bank_card.setCardCVV(CreateCvvCode());
                bank_card.setNumberCard(CreateNumberBankCard());
            }

            bankCardRepository.Add(bank_card);
        }
        catch (Exception ex)
        {
           System.out.println("Exception - "+ex);
        }
    }
    public void RemoveBankCard()
    {

    }



    //We get ExpirationDate for bankcard
    private String ExpirationDateCard()
    {
        int current_year = (LocalDate.now().getYear())-2000;//get the last two year numbers
        int current_month = LocalDate.now().getMonthValue();//get the month number

        //return data on format 1/26 where 1 - month, 26 - year
        return current_month + "/" + (current_year+6);
    }

    //This method create cvv code for bankcard(length 3 number)
    private String CreateCvvCode()
    {
        Random random = new Random();
        int cvv = random.nextInt(999);
        return "" + cvv;

    }
    //the method create number card(length
    //todo
    private String CreateNumberBankCard()
    {
        Random random = new Random();
        int numberPart1 = random.nextInt(4499);
        int numberPart2 = random.nextInt(9999);
        int numberPart3 = random.nextInt(9999);
        int numberPart4 = random.nextInt(9999);

        return "" + number;
    }

}
