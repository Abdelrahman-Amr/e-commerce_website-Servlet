package gov.iti.jets.dto;

import gov.iti.jets.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class OrderDetailsTable implements Serializable {

    List<OrderDetailsTableDto> orderDetailList;
    int pageNo;
    int pageCount;
}
