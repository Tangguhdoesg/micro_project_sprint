package com.bca.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "no_telepon")
    private String noTelepon;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_date_exp")
    private String cardDateExp;
    @Column(name = "cardccv")
    private String cardCCV;
    @Column(name = "card_type")
    private String cardType;
    @Column(name="crypto_wallet_id")
    private Integer cryptoWalletId;
    @Column(name="account_number")
    private Integer accountNumber;
    
    
    
    

}
