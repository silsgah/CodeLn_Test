package com.mtn.billing.respository;


import com.mtn.billing.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRespository extends CrudRepository<Bill, Long> {

    public List<Bill> findByEmployeeId(Integer employee_id);
}
