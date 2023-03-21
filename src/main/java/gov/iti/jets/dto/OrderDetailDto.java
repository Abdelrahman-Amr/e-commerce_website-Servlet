package gov.iti.jets.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.OrderDetail} entity
 */
@Data
@Builder
@ToString
public class OrderDetailDto extends BaseDTO {
    private  Double price;
    private  Integer quantity;
    private  String size;
    private  Double total;
    private  ProductDto product;
//    private final Double cusId;

}
