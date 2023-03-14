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
@Table(name = "product", schema = "coffee_point")
public class Product extends BaseEntity{

    @Column(name = "creation_time")
    private LocalDate creationTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catg_id")
    private Category catg;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "description", length = 250)
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}