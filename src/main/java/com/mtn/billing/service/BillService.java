package com.mtn.billing.service;

import com.mtn.billing.Util.convertutility;
import com.mtn.billing.model.Bill;
import com.mtn.billing.respository.BillRespository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRespository billRespository;

    public List<Bill> getAllBills()
    {
        List<Bill> result = (List<Bill>) billRespository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Bill>();
        }
    }
    public Bill getBillById(Long id) throws NotFoundException
    {
        Optional<Bill> bill = billRespository.findById(id);

        if(bill.isPresent()) {
            return bill.get();
        } else {
            throw new IllegalArgumentException("No bill record exist for given id");
        }
    }
    public Bill createOrUpdateBill(Bill entity)
    {
        if(entity.getId()  == 0 )
        {

            Bill bill = new Bill();
            Integer totalCost = 0;
            String date1 = entity.getDate() + " " + entity.getStartTime(), date2 = entity.getDate() + " " + entity.getEndTime();
            Integer numHours = convertutility.getHours(date1, date2);
            if (numHours != 0 || numHours == null){
                totalCost = numHours * entity.getRate();
            }
            bill.setTimeDifference(numHours);
            bill.setTotalCost(totalCost);
            bill.setRate(entity.getRate());
            bill.setDate(entity.getDate());
            bill.setEndTime(entity.getEndTime());
            bill.setStartTime(entity.getStartTime());
            bill.setProject(entity.getProject());
            bill.setEmployeeId(entity.getEmployeeId());
            entity = billRespository.save(bill);
            return entity;
        }
        else
        {
            Optional<Bill> employee = billRespository.findById(entity.getId());

            if(employee.isPresent())
            {
                Bill updateEntity = employee.get();
                Integer totalCost = 0;
                String date1 = entity.getDate() + " " + entity.getStartTime(), date2 = entity.getDate() + " " + entity.getEndTime();
                Integer numHours = convertutility.getHours(date1, date2);
                if (numHours != 0 || numHours == null){
                    totalCost = numHours * entity.getRate();
                }
                updateEntity.setTimeDifference(numHours);
                updateEntity.setTotalCost(totalCost);
                updateEntity.setDate(entity.getDate());
                updateEntity.setEndTime(entity.getEndTime());
                updateEntity.setStartTime(entity.getStartTime());
                updateEntity.setProject(entity.getProject());
                updateEntity.setEmployeeId(entity.getEmployeeId());
                updateEntity = billRespository.save(updateEntity);

                return updateEntity;
            } else {
                entity = billRespository.save(entity);
                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id) throws NotFoundException
    {
        Optional<Bill> employee = billRespository.findById(id);

        if(employee.isPresent())
        {
            billRespository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No employee record exist for given id");
        }
    }

    public List<Bill> getLawyerBill(Integer id)
    {
        List<Bill> bill = billRespository.findByEmployeeId(id);
        Integer totalCost = 0;
        int unitCost = 0;
        if(bill.size() > 0) {
            return bill;
        }
        else {
            return null;
        }
    }
    public Integer getLawyerTotalCost(Integer id) throws NotFoundException
    {
        List<Bill> bill = billRespository.findByEmployeeId(id);
        Integer totalCost = 0;
        int unitCost = 0;
        if(bill.size() > 0) {
            Integer sum = bill.stream()
                    .map(x -> (x.getTotalCost()))
                    .reduce(0, Integer::sum);
            totalCost = sum;
            return totalCost;
        }
        else {
            throw new IllegalArgumentException("No employee record exist for given id");
        }
    }
}
