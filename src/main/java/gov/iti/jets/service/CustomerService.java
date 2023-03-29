package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.Utility;
import org.mapstruct.factory.Mappers;

import java.util.*;


public class CustomerService extends BaseService<Customer>{
    private volatile static CustomerService customerService;



    CustomerMapper customerMapper;

    private CustomerDAO customerDAO;

    private CustomerService() {

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
    public Customer get(Long id)
    {
        customerDAO =  new CustomerDAO();
        return customerDAO.get(id);
    }

    public CustomerDto login(String email , String password)
    {
        customerDAO =  new CustomerDAO();
        String hashedPass = Utility.hashPassword(password);
        Customer customer = customerDAO.login(email, hashedPass);

        CustomerDto customerDto = customerMapper.toDto(customer);

        return customerDto;
    }

    public  boolean signUp(RegistrationCustomerDTO registrationCustomerDTO) {
        customerDAO =  new CustomerDAO();
        Customer customerEntity = customerMapper.toEntity(registrationCustomerDTO);
        customerEntity.setCreationTime(new Date());
        customerEntity.setIsAdmin(false);
        String hashedPass = Utility.hashPassword(registrationCustomerDTO.getPassword());
        customerEntity.setPassword(hashedPass);
        boolean result  = customerDAO.save(customerEntity);
        if(result)
        {
            customerDAO.refreshCustomer(customerEntity);
            registrationCustomerDTO.setId(customerEntity.getId());
            registrationCustomerDTO.setBirthday(customerEntity.getBirthday());
        }
        return result;
    }


    public boolean checkEmail(String email) {
        customerDAO =  new CustomerDAO();
        return customerDAO.isEmailExist(email);
    }

    public boolean updateProfile(CustomerDto customerDTO) {
        customerDAO =  new CustomerDAO();
        Customer customer = get(customerDTO.getId());
//        customer.setUserName(customerDTO.getUserName());
//        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
//        updatedCustomer.setPassword(customer.getPassword());
//        updatedCustomer.setCreationTime(customer.getCreationTime());
        customer = customerMapper.partialUpdate(customerDTO, customer);
        boolean result =  customerDAO.update(customer);
        if(result)
        {
            customerDAO.refreshCustomer(customer);
//            Customer newCustomer =customerDAO.get(customer.getId());
            customerDTO.setId(customer.getId());
            customerDTO.setBirthday(customer.getBirthday());
        }
        return result;
    }

    public List<CustomerDto> getCustomerList(int index) {
        customerDAO =  new CustomerDAO();

        List<CustomerDto> l = customerMapper.toDTOs(customerDAO.getCustomerList((index-1)*10));
        return l;
    }

    public int getRecordsCount() {
        customerDAO =  new CustomerDAO();
        return customerDAO.getRecordsCount();
    }

    public void merge(Customer customer)
    {
        customerDAO =  new CustomerDAO();
         customerDAO.merge(customer);
    }
    public void refresh(Customer customer)
    {
        customerDAO =  new CustomerDAO();
        customerDAO.refreshCustomer(customer);
    }
}

