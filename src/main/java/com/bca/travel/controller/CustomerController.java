package com.bca.travel.controller;

import com.bca.travel.model.Customer;
import com.bca.travel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public @ResponseBody List<Customer> getAllCustomer(){
        System.out.println("OK");
        return customerService.findAll();
    }
}
