package gov.iti.jets.service;

import gov.iti.jets.entity.Customer;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.Utility;


public class CustomerService extends BaseService<Customer>{

    private CustomerDAO customerDAO;
    public CustomerService() {
        customerDAO = new CustomerDAO();
        dao = customerDAO;
    }

    public Customer login(String email , String password)
    {
        String hashedPass = Utility.hashPassword(password);
        return customerDAO.login(email, hashedPass);
    }

}
