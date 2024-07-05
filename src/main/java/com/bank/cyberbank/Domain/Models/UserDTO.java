package com.bank.cyberbank.Domain.Models;

import lombok.Data;

@Data
public class UserDTO {
    int id;// id user which we chanche
    private String new_login;
    private String new_password;

}
