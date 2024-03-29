package gov.iti.jets.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "order_master", schema = "coffee_point")
public class OrderMaster extends BaseEntity{

    @Column(name = "creation_time")
    @Temporal(TemporalType.DATE)
    private LocalDate creationTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private LocalDate updateTime;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

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


    @OneToMany(mappedBy = "invo", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
