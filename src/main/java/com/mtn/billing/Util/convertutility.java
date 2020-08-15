package com.mtn.billing.Util;

import com.mtn.billing.model.CustomerResult;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class convertutility {
    public static Integer getHours(String startDate,String endDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date stdate = formatter.parse(startDate);
            Timestamp timeStampDate1 = new Timestamp(stdate.getTime());

            Date endate = formatter.parse(endDate);
            Timestamp timeStampDate2 = new Timestamp(endate.getTime());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d1 = stdate;
            Date d2 = endate;
            long diffInMilliseconds = Math.abs(d1.getTime() - d2.getTime());
            long remain = diffInMilliseconds % (24 * 60 * 60 * 1000);

            long diffHours = remain / (60 * 60 * 1000);
            return (int)diffHours;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static CustomerResult getVoucherValue(Integer amount){
        Integer customerAmount = 0, voucherValue = 0, validityDays = 0;
        CustomerResult customerResult = new CustomerResult();
        customerAmount = amount;
        if(customerAmount >= 1000 && customerAmount < 5000){
            voucherValue = 100;
            validityDays = 1;
        }else if(customerAmount >= 5000 && customerAmount < 10000){
            voucherValue = 500;
            validityDays = 5;
        }else if(customerAmount > 10000 || customerAmount == 10000){
            voucherValue = 1000;
            validityDays = 10;
        }
        customerResult.setCustomerValue(voucherValue);
        customerResult.setValueValidity(validityDays);
        return customerResult;
    }
}
