package gov.iti.jets.dto;

import gov.iti.jets.entity.Customer;
import gov.iti.jets.util.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Date;

public class OrderMasterTableDto {

    Long id;

    String customer;

    LocalDate date;

    double total;

    public OrderMasterTableDto(Long id, String customer, LocalDate date, double total) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.total = total;
    }


    public OrderMasterTableDto(Long id, String customer, double total) {
        this.id = id;
        this.customer = customer;
        this.total = total;
    }

    public OrderMasterTableDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
