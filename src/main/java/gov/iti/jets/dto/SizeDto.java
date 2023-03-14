package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.Size} entity
 */
@Data
public class SizeDto extends BaseDTO {

    private final String name;
    private final Double percentage;
}