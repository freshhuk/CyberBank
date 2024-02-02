package com.bank.cyberbank.Services;

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
                int newCardMoney = bankCard.getBalance() + loadMoney;
                bankCard.setBalance(newCardMoney);
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
    public void WithdrawMoneyFromCard()
    {

    }
    //Method for loading your money in other card
    public void LoadOwnMoneyInOtherCard()
    {

    }

    //Information your bank card
    public void BankCardInfo()
    {

    }



}
