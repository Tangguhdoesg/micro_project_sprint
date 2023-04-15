package com.bca.travel.controller;

import com.bca.travel.model.Customer;
import com.bca.travel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer(){

        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") Integer id){
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomerById(@PathVariable ("id") Integer id,@RequestBody Customer cust){
        Customer oldCust = customerService.findById(id);
        oldCust.setEmail(cust.getEmail());
        oldCust.setCardCCV(cust.getCardCCV());
        oldCust.setCardDateExp(cust.getCardDateExp());
        oldCust.setName(cust.getName());
        oldCust.setCardName(cust.getCardName());
        oldCust.setCardType(cust.getCardType());
        oldCust.setNoTelepon(cust.getNoTelepon());
        oldCust.setCardNumber(cust.getCardNumber());
        return customerService.save(oldCust);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable("id") Integer id){
        customerService.delete(customerService.findById(id));
        return "Delete Succesfull";
    }
}
