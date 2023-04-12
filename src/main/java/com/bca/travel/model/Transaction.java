package com.bca.travel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Data
@Entity
@Table
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

}
