package com.mtn.billing.service;

import com.mtn.billing.Util.convertutility;
import com.mtn.billing.model.Customer;
import com.mtn.billing.model.CustomerResult;
import com.mtn.billing.respository.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCusomter()
    {
        List<Customer> result = (List<Customer>) customerRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Customer>();
        }
    }
    public Customer getCustomerById(Long id) throws NotFoundException
    {
        Optional<Customer> bill = customerRepository.findById(id);

        if(bill.isPresent()) {
            return bill.get();
        } else {
            throw new IllegalArgumentException("No bill record exist for given id");
        }
    }
    public Customer createOrUpdateCustomer(Customer entity)
    {
        if(entity.getId()  == 0 )
        {
            Customer customer = new Customer();
            CustomerResult customerResult = new CustomerResult();
            customerResult = convertutility.getVoucherValue(entity.getCustomerOrderValue());
            if (customerResult.getCustomerValue() != 0){
                customer.setCustomerId(entity.getCustomerId());
                customer.setCustomerFirstName(entity.getCustomerFirstName());
                customer.setRedeemed(false);
                customer.setVoucherValue(customerResult.getCustomerValue());
                customer.setCustomerOrderValue(entity.getCustomerOrderValue());
                entity = customerRepository.save(customer);
            }else{
                customer = null;
            }
            return entity;
        }
        else
        {
            Optional<Customer> customerDetails = customerRepository.findById(entity.getId());
            if(customerDetails.isPresent())
            {
                Customer updateEntity = customerDetails.get();
                CustomerResult customerResult = new CustomerResult();
                customerResult = convertutility.getVoucherValue(entity.getCustomerOrderValue());
                updateEntity.setCustomerId(entity.getCustomerId());
                updateEntity.setCustomerFirstName(entity.getCustomerFirstName());
                updateEntity.setRedeemed(false);
                updateEntity.setVoucherValue(customerResult.getCustomerValue());
                updateEntity.setCustomerOrderValue(entity.getCustomerOrderValue());
                updateEntity = customerRepository.save(updateEntity);
                return updateEntity;
            } else {
                entity = customerRepository.save(entity);
                return entity;
            }
        }
    }
}
