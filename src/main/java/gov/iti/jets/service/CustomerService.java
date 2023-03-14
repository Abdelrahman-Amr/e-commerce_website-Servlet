package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDTO;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.Utility;
import jakarta.persistence.EntityManager;

import java.util.Date;


public class CustomerService extends BaseService<Customer>{

    CustomerMapper customerMapper;

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
        dao = customerDAO;
        customerMapper = new CustomerMapper();
    }

    public Customer login(String email , String password)
    {
        String hashedPass = Utility.hashPassword(password);
        return customerDAO.login(email, hashedPass);
    }

    public  boolean signUp(RegistrationCustomerDTO registrationCustomerDTO) {
        //System.out.println(customerDTO);

        Customer customerEntity = customerMapper.toEntity((CustomerDTO)registrationCustomerDTO);
        customerEntity.setCreationTime(new Date());
        System.out.println(customerEntity.getPassword());
        String hashedPass = Utility.hashPassword(registrationCustomerDTO.getPassword());
        customerEntity.setPassword(hashedPass);
        System.out.println(customerEntity);
        return customerDAO.save(customerEntity);
    }

    public boolean checkEmail(String email) {
        return customerDAO.isEmailExist(email);
    }
}
