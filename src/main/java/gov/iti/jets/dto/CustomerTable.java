package gov.iti.jets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CustomerTable implements Serializable {
    List<CustomerDto> customerDtoList;
    int pageNo;
    int pageCount;
}
