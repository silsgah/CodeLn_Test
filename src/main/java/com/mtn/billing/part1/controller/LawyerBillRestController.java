package com.mtn.billing.part1.controller;

import com.mtn.billing.model.Bill;
import com.mtn.billing.service.BillService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/bills")
@CrossOrigin
public class LawyerBillRestController {
    @Autowired
    private BillService billService;

    @GetMapping
    public Iterable<Bill> getAllBill(){
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Iterable<Bill> getEmployeeById(@PathVariable Integer id) throws NotFoundException {
        List<Bill> bill = billService.getLawyerBill(id);
        return bill;
    }
    @PostMapping
    public ResponseEntity<?> addBill(@Valid  @RequestBody Bill bill, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Bill newbill = billService.createOrUpdateBill(bill);
        return new ResponseEntity<Bill>(newbill, HttpStatus.CREATED);
    }
}
