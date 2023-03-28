package gov.iti.jets.dto;

import gov.iti.jets.entity.OrderMaster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class OrderTable implements Serializable {
    List<OrderMasterTableDto> orderList;
    int pageNo;
    int pageCount;
}
