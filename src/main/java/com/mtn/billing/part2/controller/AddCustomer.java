package com.mtn.billing.part2.controller;

import com.mtn.billing.model.Customer;
import com.mtn.billing.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class AddCustomer {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Customer> getAllBill(){
        return customerService.getAllCusomter();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) throws NotFoundException {
        Customer bill = customerService.getCustomerById(id);
        return bill;
    }
    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Customer newbill = customerService.createOrUpdateCustomer(customer);
        return new ResponseEntity<Customer>(newbill, HttpStatus.CREATED);
    }
}
