package com.bca.travel.repository;

import com.bca.travel.model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	
	@Query("select t from Transaction t")
	List<Transaction> customFindAll();
}
