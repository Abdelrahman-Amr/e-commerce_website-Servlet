package gov.iti.jets.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="category")
public class Category extends BaseEntity{
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    private String description;

    @Column(name="image_url")
    private String imageUrl ;

    private String name ;

    private Integer priority ;
    private Boolean active ;
    @OneToMany(mappedBy="category",cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
    })
    private List<Product> products;
}
