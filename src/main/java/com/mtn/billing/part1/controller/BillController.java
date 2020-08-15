package com.mtn.billing.part1.controller;

import com.mtn.billing.model.Bill;
import com.mtn.billing.model.FinanceBill;
import com.mtn.billing.service.BillService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/all")
    public Iterable<Bill> getAllPTs(){
        return billService.getAllBills();
    }

    @RequestMapping
    public String getAllEmployees(Model model)
    {
        List<Bill> list = billService.getAllBills();
        model.addAttribute("bills", list);
        return "index";
    }
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws NotFoundException
    {
        if (id.isPresent()) {
            Bill entity = billService.getBillById(id.get());
            model.addAttribute("bill", entity);
        } else {
            model.addAttribute("bill", new Bill());
        }
        return "add-bill";
    }

    @RequestMapping(path = "/createBill", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Bill bill)
    {
        billService.createOrUpdateBill(bill);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id)
            throws NotFoundException
    {
        billService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public Iterable<Bill> getEmployeeById(@PathVariable Integer id) throws NotFoundException {
        List<Bill> bill = billService.getLawyerBill(id);
        return bill;
    }
    @RequestMapping(path = "/finance")
    public String getFinanceView(Model model){
        model.addAttribute("bill", new FinanceBill());
        return "lawyers";
    }
    @RequestMapping(path = "/searchEmployee", method = RequestMethod.POST)
    public String searchEmployee(Model model,FinanceBill financeBill) throws NotFoundException
    {
        if(financeBill.getEmployeeId() != null){
            try
            {
                List<Bill> employeebill = billService.getLawyerBill(financeBill.getEmployeeId());
                Integer totalCost = billService.getLawyerTotalCost(financeBill.getEmployeeId());
                model.addAttribute("employeebill", employeebill);
                model.addAttribute("totalCost", totalCost);
                model.addAttribute("totalText", "Total Cost");
            }catch (Exception exception) {
               System.out.println(exception.getCause());
                model.addAttribute("employeebill", new Bill());
                model.addAttribute("totalCost", 0);
                model.addAttribute("totalText", "Total Cost");
            }
        }else{
            model.addAttribute("employeebill", new Bill());
            model.addAttribute("totalCost", 0);
            model.addAttribute("totalText", "Total Cost");
        }
        return "employee";
    }
}
