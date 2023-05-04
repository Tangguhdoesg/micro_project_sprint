package com.bca.travel.model;

import java.util.Date;

import lombok.Data;

@Data
public class CCPaymentEntity {

    private String messageType;
    private String cardNumber;
    private Integer cvv;
    private String expDate;
    private Double amount;
    private Integer merchantId;
    private Integer refTransaction;
    private Date dateTransaction;
    private String status;
    private Integer approvalCode;
    private Integer transactionId;
}
