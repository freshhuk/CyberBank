package com.bank.cyberbank.Services;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private static final String STATUSCODE200_MESSAGE = "Successful";
    private static final String STATUSCODE404_MESSAGE = "Bank card not found";
    private final BankCardRepository repository;

    @Autowired
    public BankService(BankCardRepository repository) {
        this.repository = repository;
    }

    //Method for loading your money in your bank card
    public String LoadOwnMoney(String numberBankCard, int loadMoney) {
        try {
            var bankCard = repository.GetBankCardByNumber(numberBankCard);
            if (bankCard != null && loadMoney >= 0) {
                //updating money
                int updatedCardMoney = bankCard.getBalance() + loadMoney;
                bankCard.setBalance(updatedCardMoney);
                //Save changes
                repository.saveEntity(bankCard);

                return STATUSCODE200_MESSAGE;
            }
            return STATUSCODE404_MESSAGE;
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }

    //Method for Withdraw money from your bank card
    public String WithdrawMoneyFromCard(String numberBankCard, int withdrawMoney) {
        try {
            var bankCard = repository.GetBankCardByNumber(numberBankCard);
            if (bankCard != null && checkBalance(bankCard, withdrawMoney)) {
                int updatedCardMoney = bankCard.getBalance() - withdrawMoney;
                bankCard.setBalance(updatedCardMoney);
                //Save changes
                repository.saveEntity(bankCard);
                return STATUSCODE200_MESSAGE;
            }
            return STATUSCODE404_MESSAGE;
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }

    //Method for loading your money in other card
    public String loadOwnMoneyInOtherCard(String ownNumberBankCard, String anotherNumberBankCard, int loadMoney) {
        try {
            if (ownNumberBankCard != null && anotherNumberBankCard != null) {
                //Get cards on dadabase
                var ownBankCard = repository.GetBankCardByNumber(ownNumberBankCard);
                var anotherBankCard = repository.GetBankCardByNumber(anotherNumberBankCard);

                if (ownBankCard != null && anotherBankCard != null && checkBalance(ownBankCard, loadMoney)) {

                    int newBalance = ownBankCard.getBalance() - loadMoney;
                    ownBankCard.setBalance(newBalance);
                    repository.saveEntity(ownBankCard);

                    int anotherNewBalanc = anotherBankCard.getBalance() + loadMoney;
                    anotherBankCard.setBalance(anotherNewBalanc);
                    repository.saveEntity(anotherBankCard);
                    return STATUSCODE200_MESSAGE;
                }
            }
            return "Bank cards not found";
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return "Error";
        }
    }

    //Information your bank card
    public BankCard bankCardInfo(String numberBankCard) {
        try {
            return repository.GetBankCardByNumber(numberBankCard);
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return null;
        }
    }

    private boolean checkBalance(BankCard bankCard, int money) {
        try {
            return bankCard.getBalance() >= money;
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
            return false;
        }
    }

}
