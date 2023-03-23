package gov.iti.jets.dto;

import jakarta.servlet.http.Part;
import lombok.*;

@Data
public class AdminProductDto extends BaseDTO {
    private final String name;
    private final String category;
    private final Double price;
    private final Double discount;
    private final Integer quantity;
    private final Integer priority;
    private final String description;
    //private final Part imagePart;
}
