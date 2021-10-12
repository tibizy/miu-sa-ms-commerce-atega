package miu.sa.accountservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    //role
    private boolean isActive;
    @OneToMany(
            cascade=CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = Address.class
    )
    private List<Address> addresses;
    @OneToMany(
            cascade=CascadeType.ALL,
//            fetch = FetchType.EAGER,
            targetEntity = PaymentMethod.class
    )
    private List<PaymentMethod> payments;
}
