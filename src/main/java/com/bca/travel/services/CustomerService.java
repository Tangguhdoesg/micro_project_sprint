package com.bca.travel.services;

import com.bca.travel.model.Customer;
import com.bca.travel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public Customer save(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer findById(Integer id){
        return customerRepo.findById(id).get();
    }

    public List<Customer> findAll(){
        return customerRepo.findAll();

    }

    public void delete(Customer customer){
        customerRepo.delete(customer);
    }
}
