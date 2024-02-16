package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService
{

    private final BankCardRepository repository;
    @Autowired
    public BankService(BankCardRepository repository)
    {
        this.repository = repository;
    }


    //Method for loading your money in your bank card
    public String LoadOwnMoney(String numberBankCard, int loadMoney)
    {
        try{
            var bankCard = repository.GetBankCardByNumber(numberBankCard);
            if(bankCard != null)
            {
                //updating money
                int updatedCardMoney = bankCard.getBalance() + loadMoney;
                bankCard.setBalance(updatedCardMoney);
                //Save changes
                repository.saveEntity(bankCard);

                return "Successful";
            }
            return "Bank card not found";
        }
        catch (Exception ex)
        {
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }
    //Method for Withdraw money from your bank card
    public String WithdrawMoneyFromCard(String numberBankCard, int withdrawMoney)
    {
        try{
            var bankCard = repository.GetBankCardByNumber(numberBankCard);
            if(bankCard != null)
            {
                int updatedCardMoney = bankCard.getBalance() - withdrawMoney;
                bankCard.setBalance(updatedCardMoney);
                //Save changes
                repository.saveEntity(bankCard);
                return "Successful";
            }
            return "Bank card not found";
        }
        catch (Exception ex){
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }
    //Method for loading your money in other card
    public String loadOwnMoneyInOtherCard(String ownNumberBankCard, String numberBankCard, int loadMoney)
    {
        try{
            if(ownNumberBankCard != null && numberBankCard != null){
                //Get cards on dadabase
                var ownBankCard = repository.GetBankCardByNumber(ownNumberBankCard);
                var anotherBankCard = repository.GetBankCardByNumber(numberBankCard);

                if(ownBankCard != null && anotherBankCard != null){

                    int newBalance = ownBankCard.getBalance() - loadMoney;
                    ownBankCard.setBalance(newBalance);
                    repository.saveEntity(ownBankCard);

                    int anotherNewBalanc = anotherBankCard.getBalance() + loadMoney;
                    anotherBankCard.setBalance(anotherNewBalanc);
                    repository.saveEntity(anotherBankCard);
                    return "Successful";
                }
            }
            return "Bank cards not found";
        }
        catch (Exception ex){
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }

    //Information your bank card
    public BankCard BankCardInfo(String numberBankCard)
    {
        try{
            return repository.GetBankCardByNumber(numberBankCard);
        }
        catch (Exception ex)
        {
            System.out.println("Exception - " + ex);
            return null;
        }
    }



}
