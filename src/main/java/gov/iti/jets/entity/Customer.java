package gov.iti.jets.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Entity
@Table(name="customer")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Column(name = "name")
    private String userName;

    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    private String address;

    @Column(name = "phone_no")
    private String phone;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String email;

    private String password;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @Column(name = "is_admin")
    private Boolean isAdmin;


}
