package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.util.MyLocal;
import gov.iti.jets.util.Utility;
import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.*;


public class CustomerService extends BaseService<Customer>{
    private volatile static CustomerService customerService;

    static List<CustomerDto> customerDtoList = new ArrayList<>();

    Map<Integer,List<CustomerDto>> customerDtoMap = new HashMap<>();

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
        Customer customer = customerDAO.login(email, hashedPass);

        CustomerDto customerDto = customerMapper.toDto(customer);

        return customerDto;
    }

    public  boolean signUp(RegistrationCustomerDTO registrationCustomerDTO) {
        Customer customerEntity = customerMapper.toEntity(registrationCustomerDTO);
        customerEntity.setCreationTime(new Date());
        String hashedPass = Utility.hashPassword(registrationCustomerDTO.getPassword());
        customerEntity.setPassword(hashedPass);
        boolean result  = customerDAO.save(customerEntity);
        if(result)
        {
            customerDAO.refresh(customerEntity);
            Customer customer =customerDAO.get(customerEntity.getId());
            registrationCustomerDTO.setId(customerEntity.getId());
            registrationCustomerDTO.setBirthday(customerEntity.getBirthday());
        }
        return result;
    }

    public void setManager(EntityManager manager)
    {
        this.customerDAO.setManager(manager);
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
        boolean result =  customerDAO.update(customer);
        if(result)
        {
            customerDAO.refresh(customer);
            Customer newCustomer =customerDAO.get(customer.getId());
            customerDTO.setId(newCustomer.getId());
            customerDTO.setBirthday(newCustomer.getBirthday());
        }
        return result;
    }

    public List<CustomerDto> getCustomerList(int index) {

        List<CustomerDto> l = customerMapper.toDTOs(customerDAO.getCustomerList((index-1)*10));
//        l.forEach((e)->System.out.println(e.getUserName()));
//        System.out.println(l);
        return l;
    }

    public int getRecordsCount() {
        return customerDAO.getRecordsCount();
    }

//    void getAllCustomer() {
//        customerDtoList = customerMapper.toDTOs(customerDAO.findAll());
//    }
//
//    void buildCustomerMap() {
//        int endInd=10,index=1;
//        while(customerDtoList.size()>0) {
//            if(customerDtoList.size()<10) {
//                endInd=customerDtoList.size();
//            }
//            customerDtoMap.put(index,customerDtoList.subList(0,endInd));
//                    index++;
//        }
//    }
}
