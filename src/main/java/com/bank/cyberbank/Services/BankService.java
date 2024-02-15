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
    //Todo сделать сохранение денег в бд и их изминение а то сейчас операция
    // прохожит успешно но деньги не сохраняються
    public String LoadOwnMoney(String numberBankCard, int loadMoney)
    {
        try{
            var bankCard = repository.GetBankCardByNumber(numberBankCard);
            if(bankCard != null)
            {
                int updatedCardMoney = bankCard.getBalance() + loadMoney;
                bankCard.setBalance(updatedCardMoney);
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
    public void LoadOwnMoneyInOtherCard(String ownNumberBankCard,String numberBankCard,int loadMoney)
    {

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
