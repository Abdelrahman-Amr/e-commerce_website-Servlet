package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.MyLocal;
import gov.iti.jets.util.Utility;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.Date;


public class CustomerService extends BaseService<Customer>{
    private volatile static CustomerService customerService;

    CustomerMapper customerMapper;

    private CustomerDAO customerDAO;

    private CustomerService() {
        customerDAO =  CustomerDAO.getInstance();
        dao = customerDAO;
        customerMapper = Mappers.getMapper(CustomerMapper.class);
    }
    public static CustomerService getInstance() {
        if (customerService == null) {
            synchronized (CustomerService.class) {
                if (customerService == null) {
                    customerService = new CustomerService();
                }
            }
        }
        return customerService;
    }

    public CustomerDto login(String email , String password)
    {
        String hashedPass = Utility.hashPassword(password);
        return customerMapper.toDto(customerDAO.login(email, hashedPass));
    }

    public  boolean signUp(RegistrationCustomerDTO registrationCustomerDTO) {
        //System.out.println(customerDTO);

        Customer customerEntity = customerMapper.toEntity(registrationCustomerDTO);
        customerEntity.setCreationTime(new Date());
        System.out.println(customerEntity.getPassword());
        String hashedPass = Utility.hashPassword(registrationCustomerDTO.getPassword());
        customerEntity.setPassword(hashedPass);
        System.out.println(customerEntity);
        return customerDAO.save(customerEntity);
    }

    public void setManager(EntityManager manager)
    {
        this.customerDAO.setManager(manager);
    }

    public boolean checkEmail(String email) {
        return customerDAO.isEmailExist(email);
    }
}
