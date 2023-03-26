package gov.iti.jets.dto;

import gov.iti.jets.entity.Category;
import lombok.Data;

/**
 * A DTO for the {@link gov.iti.jets.entity.Product} entity
 */
@Data
public class ProductDto extends BaseDTO {
    private String name;
    private Long catg_id;
    private Double price;
    private Double discount;
    private Integer quantity;
    private Integer priority;
    private String description;
    private String imageUrl;

}
