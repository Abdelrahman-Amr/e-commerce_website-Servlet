package gov.iti.jets.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends BaseDTO{
    private String userName;

    private String address;

    private String phone;

    private Date birthday;

    private String email;

    private int creditLimit;
}
