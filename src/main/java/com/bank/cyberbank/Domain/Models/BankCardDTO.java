package com.bank.cyberbank.Domain.Models;


import lombok.Data;

@Data
public class BankCardDTO
{
    int id;// id card which we chanche
    String ExpirationDate;
    String NameOwnerCard;
    String LastNameOwnerCard;

}
