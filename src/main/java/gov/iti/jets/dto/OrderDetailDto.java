package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.OrderDetail} entity
 */
@Data
public class OrderDetailDto extends BaseDTO {
    private final Double price;
    private final Double quantity;
    private final String size;
    private final Double total;
    private final ProductDto product;
//    private final Double cusId;
}
