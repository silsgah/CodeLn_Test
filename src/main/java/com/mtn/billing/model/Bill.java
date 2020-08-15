package com.mtn.billing.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Employee Id is mandatory")
    @Column(name="employee_id")
    private Integer employeeId;
    @NotBlank(message = "Project name is mandatory")
    @Column(name="Project")
    private String project;
    @NotBlank(message = "Date is mandatory format(YYYY-MM-dd)")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="Date")
    private String date;
    @Pattern(regexp = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$", message = "Time must be in (HH:MM) format")
    @NotBlank(message = "Start time is mandatory.244-Hours format(HH:MM)")
    @Column(name="start_time")
    private String startTime;
    @Pattern(regexp = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$", message = "Time must be in (HH:MM) format")
    @NotBlank(message = "End time is mandatory.24-Hours format(HH:MM)")
    @Column(name="end_time")
    private String endTime;
    @Column(name="Rate")
    private Integer rate;
    @Column(name="time_difference")
    private Integer timeDifference;
    @Column(name="total_cost")
    private Integer totalCost;

    public Bill() {
    }

    public Bill(long id, Integer employeeId, String project, String date, String startTime, String endTime, Integer rate, Integer timeDifference, Integer totalCost) {
        this.id = id;
        this.employeeId = employeeId;
        this.project = project;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
        this.timeDifference = timeDifference;
        this.totalCost = totalCost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(Integer timeDifference) {
        this.timeDifference = timeDifference;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}
