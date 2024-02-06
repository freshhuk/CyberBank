package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    public String CreateBankCard(String nameOwnerCard, String lastNameOwnerCard)
    {
        try{
            BankCard bank_card = new BankCard();
            {
                bank_card.setNameOwnerCard(nameOwnerCard);
                bank_card.setLastNameOwnerCard(lastNameOwnerCard);

                bank_card.setBalance(0);
                bank_card.setExpirationDate(ExpirationDateCard());
                bank_card.setCardCVV(CreateCvvCode());
                bank_card.setNumberCard(CreateNumberBankCard());
            }

            bankCardRepository.Add(bank_card);
            return "Successful";
        }
        catch (Exception ex)
        {
            System.out.println("Exception - "+ex);
            return "Error";
        }
    }
    public String RemoveBankCard(String BankCard_Number)
    {
        try{
            if(BankCard_Number != null)
            {
                var bank_card = bankCardRepository.GetBankCardByNumber(BankCard_Number);
                bankCardRepository.Delete(bank_card.getId());
                return "Successful";
            }
            return "BankCard_Number is null";
        }
        catch (Exception ex)
        {
            System.out.println("Exception - "+ex);
            return "Error";
        }
    }

    public List<BankCard> GetAllCard()
    {
        try{
            return bankCardRepository.AllCards();
        }
        catch (Exception ex)
        {
            System.out.println("Exception - " + ex);
            return null;
        }
    }

    public String UpdateOwnerCard(BankCardDTO update_card)
    {
        try{
            if(update_card != null)
            {
                bankCardRepository.Update(update_card);
                return "Successful";
            }
            return "Model is null";
        }
        catch (Exception ex)
        {
            System.out.println("Exception - " + ex);
            return "Error";
        }
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
    //the method create number card
    private String CreateNumberBankCard()
    {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for(int i = 0; i < 4; i++)
        {
            int numberPart = random.nextInt(500)+4000;
            number.append(numberPart).append(" ");
        }

        return number.toString();
    }

}
