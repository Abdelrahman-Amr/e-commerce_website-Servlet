package gov.iti.jets.dto;

import gov.iti.jets.mapper.BaseMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.Category} entity
 */
@Data
public class CategoryDto extends BaseDTO {
    private final String description;
    private final String imageUrl;
    private final String name;
    private final Integer priority;
    private final Boolean active;
}