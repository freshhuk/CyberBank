package com.bank.cyberbank.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bankcards")
public class BankCard {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @Column(name = "numberCard")
    String NumberCard;
    @Column(name = "cardcv")
    String CardCVV;
    @Column(name = "expirationdate")
    String ExpirationDate;
    @Column(name = "nameownercard")
    String NameOwnerCard;
    @Column(name = "lastnameownercard")
    String LastNameOwnerCard;
    @Column(name = "balance")
    int Balance;

}
