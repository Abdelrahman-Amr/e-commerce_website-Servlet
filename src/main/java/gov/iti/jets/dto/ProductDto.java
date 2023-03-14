package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.jets.entity.Product} entity
 */
@Data
public class ProductDto extends BaseDTO {
    private final LocalDate creationTime;
    private final Double price;
    private final Double discount;
    private final String imageUrl;
    private final Double quantity;
    private final String name;
    private final Boolean active;
    private final Integer priority;
    private final String description;
}