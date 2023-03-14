package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Customer} entity
 */
@Data
public class CustomerDto implements Serializable {
    private final Long id;
    private final String userName;
    private final String address;
    private final String phone;
    private final Date birthday;
    private final String email;
    private final String password;
    private final int creditLimit;
}