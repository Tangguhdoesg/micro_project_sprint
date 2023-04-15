package com.bca.travel.controller;

import com.bca.travel.model.Transaction;
import com.bca.travel.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransaction(){
        return transRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction getById(@PathVariable("id") Integer id){
        return transRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable("id") Integer id){
        transRepo.deleteById(id);
        return "Delete ok";
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Transaction createTransaction(Transaction transaction){
        return transRepo.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable("id") Integer id, @RequestBody Transaction transaction){
        Transaction oldTran = transRepo.findById(id).get();
        oldTran.setTransactionDate(transaction.getTransactionDate());
        oldTran.setQuantity(transaction.getQuantity());
        oldTran.setCustomerId(transaction.getCustomerId());
        oldTran.setHolidayPackageId(transaction.getHolidayPackageId());
        oldTran.setTotalPrice(transaction.getTotalPrice());
        transRepo.save(oldTran);
        return oldTran;
    }



}
