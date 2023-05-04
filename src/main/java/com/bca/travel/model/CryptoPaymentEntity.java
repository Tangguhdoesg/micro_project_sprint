package com.bca.travel.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class CryptoPaymentEntity {
	
	private String messageType;
    private String merchantName;
    private String originalCurrency;
    private Double originalAmount;
    private String transactionType;
    private String referenceNumber;
    private Integer accountNumber;
    private Integer cardPaymentId;
    private String currency;
    private Double amount;
    private Double conversionRate;
    private Date transactionDate;
    private String status;
    private String tranCode;
    private Integer paymentId;

}
