package gov.iti.jets.mapper;

import gov.iti.jets.dto.CustomerDTO;
import gov.iti.jets.entity.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerMapper implements BaseMapper<Customer, CustomerDTO>{
    @Override
    public CustomerDTO toDTO(Customer entity) {

        CustomerDTO dto= CustomerDTO.builder()
                .userName(entity.getUserName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .birthday(entity.getBirthday())
                .email(entity.getEmail())
                .creditLimit(entity.getCreditLimit())
                .build();
        dto.setId(entity.getId());
        return  dto;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        Customer entity= new Customer();
        if(dto!=null)
        {
            //entity.setId(dto.getId());
            entity.setUserName(dto.getUserName());
            entity.setAddress(dto.getAddress());
            entity.setPhone(dto.getPhone());
            entity.setBirthday(dto.getBirthday());
            entity.setEmail(dto.getEmail());
            entity.setCreditLimit(dto.getCreditLimit());
        }
        return  entity;
    }

    @Override
    public ArrayList<CustomerDTO> toDTOs(Collection<Customer> customers) {
        return (ArrayList<CustomerDTO>) customers.
                stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<Customer> toEntities(Collection<CustomerDTO> customerDTOS) {
        return (ArrayList<Customer>) customerDTOS.
                stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
