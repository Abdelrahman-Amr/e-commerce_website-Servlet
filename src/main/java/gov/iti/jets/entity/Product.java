package gov.iti.jets.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    private Double price;
    private Double discount;

    @Column(name = "image_url")
    private String imageURL;
    private Double quantity ;
    private String name;
    private Boolean active;
    private Integer priority;
    private String description;

    @ManyToOne
    @JoinColumn(name = "catg_id" )
    private Category category;

}
