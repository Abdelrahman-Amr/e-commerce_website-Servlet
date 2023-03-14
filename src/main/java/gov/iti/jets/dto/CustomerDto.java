package gov.iti.jets.dto;

import gov.iti.jets.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Customer} entity
 */
@Data
public class CustomerDto extends BaseEntity {

    private  String userName;
    private  String address;
    private  String phone;
    private  Date birthday;
    private  String email;
    private  int creditLimit;
}
