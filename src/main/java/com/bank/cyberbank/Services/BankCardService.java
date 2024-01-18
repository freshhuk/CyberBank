package com.bank.cyberbank.Services;

import com.bank.cyberbank.Repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void CreateBankCard()
    {
        //code
    }
    public void RemoveBankCard()
    {

    }

}
