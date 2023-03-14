package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.Utility;
import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Date;


public class CustomerService extends BaseService<Customer>{

    CustomerMapper customerMapper;

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
        dao = customerDAO;
        customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    public CustomerDto login(String email , String password)
    {
        String hashedPass = Utility.hashPassword(password);
        Customer customer = customerDAO.login(email, hashedPass);
        //System.out.println("---------------");
//        System.out.println(customer.getEmail()+" "+customer.getUserName()+" "+customer.getId());
//        System.out.println(customer.getPassword()+" "+customer.getPhone()+" "+customer.getAddress());
        //System.out.println(customer);
        CustomerDto customerDto = customerMapper.toDto(customer);
//        System.out.println("-----------------------------");
//        System.out.println("dto");
//        System.out.println(customerDto);
        return customerDto;
    }

    public  boolean signUp(RegistrationCustomerDTO registrationCustomerDTO) {
        //System.out.println(customerDTO);
        Customer customerEntity = customerMapper.toEntity(registrationCustomerDTO);
        customerEntity.setCreationTime(new Date());
        //System.out.println(customerEntity.getPassword());
        String hashedPass = Utility.hashPassword(registrationCustomerDTO.getPassword());
        customerEntity.setPassword(hashedPass);
        //System.out.println(customerEntity);
        return customerDAO.save(customerEntity);
    }

    public boolean checkEmail(String email) {
        return customerDAO.isEmailExist(email);
    }

    public boolean updateProfile(CustomerDto customerDTO) {
        Customer customer = get(customerDTO.getId());
//        customer.setUserName(customerDTO.getUserName());
//        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
//        updatedCustomer.setPassword(customer.getPassword());
//        updatedCustomer.setCreationTime(customer.getCreationTime());
        customer = customerMapper.partialUpdate(customerDTO, customer);
        return customerDAO.update(customer);
        //return false;
    }
}
