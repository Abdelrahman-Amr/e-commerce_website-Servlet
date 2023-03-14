package gov.iti.jets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "size", schema = "coffee_point")
public class Size extends BaseEntity{

    @Column(name = "creation_time")
    private LocalDate creationTime;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private Double percentage;

}