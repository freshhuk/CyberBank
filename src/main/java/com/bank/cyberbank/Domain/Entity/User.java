package com.bank.cyberbank.Domain.Entity;

import com.bank.cyberbank.Domain.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "users_seq_generator")
    @SequenceGenerator(name="users_seq_generator", sequenceName = "users_seq", allocationSize=1)
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private Role role;
}
