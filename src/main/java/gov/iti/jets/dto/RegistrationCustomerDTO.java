package gov.iti.jets.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationCustomerDTO extends CustomerDTO {
    private String password;
}
