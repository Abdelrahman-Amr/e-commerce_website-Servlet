package gov.iti.jets.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.jets.entity.OrderMaster} entity
 */
@Data
public class OrderMasterDto extends BaseDTO {

    private final LocalDate date;
    private final Boolean isDone;
    private final Double shipping;
    private final Integer status;
    private final Double total;
    private final String nameOfUser;
}