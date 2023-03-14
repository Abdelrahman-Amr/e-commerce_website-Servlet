package gov.iti.jets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "category", schema = "coffee_point")
public class Category extends BaseEntity{

    @Column(name = "creation_time")
    private LocalDate creationTime;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "catg")
    private Set<Product> products = new LinkedHashSet<>();

}