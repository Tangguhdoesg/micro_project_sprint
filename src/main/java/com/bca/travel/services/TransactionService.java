package com.bca.travel.services;

import com.bca.travel.model.Transaction;
import com.bca.travel.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    public Transaction save(Transaction transaction){
        return transactionRepo.save(transaction);
    }

    public Transaction findById(Integer id){
        return transactionRepo.findById(id).get();
    }

    public List<Transaction> findAll(){
        return transactionRepo.customFindAll();
    }

    public void delete(Transaction transaction){
        transactionRepo.delete(transaction);
    }
    
}
