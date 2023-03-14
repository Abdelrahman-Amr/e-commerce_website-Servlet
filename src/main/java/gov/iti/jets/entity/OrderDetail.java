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
@Table(name = "order_details", schema = "coffee_point")
public class OrderDetail extends BaseEntity{

    @Column(name = "creation_time")
    private LocalDate creationTime;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "size")
    private String size;

    @Column(name = "total")
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invo_id")
    private OrderMaster invo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "cus_id")
    private Double cusId;

}