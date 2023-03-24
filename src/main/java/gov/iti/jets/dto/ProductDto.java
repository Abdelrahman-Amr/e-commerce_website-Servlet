package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.jets.entity.Product} entity
 */
@Data
public class ProductDto extends BaseDTO {
    private final String name;
    private final Long categoryId;
    private final Double price;
    private final Double discount;
    private final Integer quantity;
    private final Integer priority;
    private final String description;
    private final String imageUrl;
}
