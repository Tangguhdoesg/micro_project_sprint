package com.bca.travel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="transactiondata")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customerId")
    private Integer customerId;
    @Column(name = "holidayPackageId")
    private Integer holidayPackageId;
    @Column(name = "totalPrice")
    private Integer totalPrice;
    @Column(name = "transactionDate")
    private Timestamp transactionDate;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name="currency")
    private String currency;
    @Column(name="ammount")
    private Double ammount;
    @Column(name="type")
    private String type;
    @Column(name="status")
    private String status;

}
