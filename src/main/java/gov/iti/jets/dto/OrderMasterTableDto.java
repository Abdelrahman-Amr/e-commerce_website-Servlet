package gov.iti.jets.dto;

import gov.iti.jets.entity.Customer;
import gov.iti.jets.util.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Date;

public class OrderMasterTableDto {

    Long id;

    String customerEmail;
    String customerAddress;

    Date date;

    double total;

    public OrderMasterTableDto(Long id, String customerEmail, String customerAddress, Date date, double total) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.date = date;
        this.total = total;
    }


    public OrderMasterTableDto(Long id, String customerEmail, String customerAddress, double total) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
