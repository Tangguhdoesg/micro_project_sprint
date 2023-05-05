package com.bca.travel.controller;

import com.bca.travel.model.CCPaymentEntity;
import com.bca.travel.model.CryptoPayemntGatewayAuth;
import com.bca.travel.model.Customer;
import com.bca.travel.model.Transaction;
import com.bca.travel.services.CustomerService;
import com.bca.travel.services.KafkaService;
import com.bca.travel.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired 
	TransactionService transService;
	
	@Autowired CustomerService custServ;
	
	@Autowired KafkaService kafkaService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransaction(){
        return transService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction getById(@PathVariable("id") Integer id){
        return transService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable("id") Integer id){
    	Transaction data = getById(id);
        transService.delete(data);
        return "Delete ok";
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Transaction createTransaction(@RequestBody Transaction transaction){
    	System.out.println(transaction.getTransactionDate());
    	transaction = transService.save(transaction);
    	sendTransaction(transaction);
    	return transaction;
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable("id") Integer id, @RequestBody Transaction transaction){
        Transaction oldTran = transService.findById(id);
        oldTran.setTransactionDate(transaction.getTransactionDate());
        oldTran.setQuantity(transaction.getQuantity());
        oldTran.setCustomerId(transaction.getCustomerId());
        oldTran.setHolidayPackageId(transaction.getHolidayPackageId());
        oldTran.setTotalPrice(transaction.getTotalPrice());
        transService.save(oldTran);
        return oldTran;
    }
    
    public void sendTransaction(Transaction tran) {
    	Customer dataCustomer = custServ.findById(tran.getCustomerId());
    	System.out.println("INI TRANS TYPE : " + tran.getType());
    	if(tran.getType().equalsIgnoreCase("Crypto")) {
        	CryptoPayemntGatewayAuth cryptoEntity = new CryptoPayemntGatewayAuth();
        	cryptoEntity.setAccountNumber(dataCustomer.getAccountNumber());
        	cryptoEntity.setMessageType("Request");
        	cryptoEntity.setMerchantName("TRAVEL APPLICATION");
//        	cryptoEntity.setOriginalCurrency(tran.getCurrency()); //what is this
        	cryptoEntity.setOriginalCurrency("BTC"); //what is this
        	cryptoEntity.setAmount(tran.getTotalPrice().doubleValue());
        	cryptoEntity.setTransactionType("Sell");
        	cryptoEntity.setReferenceNumber(UUID.randomUUID().toString());
        	cryptoEntity.setCardPaymentId(dataCustomer.getCryptoWalletId());
        	cryptoEntity.setCurrency(tran.getCurrency());
        	
        	cryptoEntity.setConversionRate(Double.valueOf("10"));
        	cryptoEntity.setTransactionDate(new Date());
        	cryptoEntity.setStatus(tran.getStatus());
        	cryptoEntity.setTranCode("CYP");
        	cryptoEntity.setPaymentId(tran.getId());
        	cryptoEntity.setOriginalAmount(tran.getAmmount());
        	
        	kafkaService.paymentCrypto(cryptoEntity);
    	}else {
    		CCPaymentEntity ccPayment = new CCPaymentEntity();
    		ccPayment.setMessageType("Request");
    		ccPayment.setCardNumber(dataCustomer.getCardNumber());
    		ccPayment.setCvv(Integer.valueOf(dataCustomer.getCardCCV()));
    		ccPayment.setExpDate(dataCustomer.getCardDateExp());
    		ccPayment.setAmount(tran.getAmmount());
    		ccPayment.setMerchantId(1);
    		ccPayment.setRefTransaction(tran.getId());
    		ccPayment.setDateTransaction(new Date());
    		ccPayment.setStatus(tran.getStatus());
    		ccPayment.setApprovalCode(1);
    		ccPayment.setTransactionId(tran.getId());
    		
    		kafkaService.paymentCC(ccPayment);
    	}

    	
    }

    

}
