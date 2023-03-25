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
@Table(name = "order_master", schema = "coffee_point")
public class OrderMaster extends BaseEntity{

    @Column(name = "creation_time")
    private LocalDate creationTime;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_done")
    private Boolean isDone;

    @Column(name = "shipping")
    private Double shipping;

    @Column(name = "status")
    private Integer status;

    @Column(name = "total")
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer cust;

    @Column(name = "name_of_user")
    private String nameOfUser;

    @Column(name = "is_cart")
    private  Boolean isCart;


    @OneToMany(mappedBy = "invo")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}
