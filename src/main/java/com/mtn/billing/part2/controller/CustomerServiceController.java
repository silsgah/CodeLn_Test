package com.mtn.billing.part2.controller;

import com.mtn.billing.model.Customer;
import com.mtn.billing.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerServiceController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping
    public String getAllEmployees(Model model)
    {
        List<Customer> list = customerService.getAllCusomter();
        model.addAttribute("customers", list);
        return "customer";
    }
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws NotFoundException
    {
        if (id.isPresent()) {
            Customer entity = customerService.getCustomerById(id.get());
            model.addAttribute("customer", entity);
        } else {
            model.addAttribute("customer", new Customer());
        }
        return "add-customer";
    }

    @RequestMapping(path = "/createCustomer", method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer)
    {
        customerService.createOrUpdateCustomer(customer);
        return "redirect:/customer";
    }
}
