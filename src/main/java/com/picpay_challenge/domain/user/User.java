package com.picpay_challenge.domain.user;

import com.picpay_challenge.dto.CreateUserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(CreateUserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.email = data.email();
        this.balance = data.balance();
        this.userType = data.type();
        this.password = data.password();
    }
    public void addBalance(BigDecimal amount) {
        BigDecimal newBalance = this.balance.add(amount);
        this.setBalance(newBalance);
    }

    public void subtractBalance(BigDecimal amount) {
        BigDecimal newBalance =  this.balance.subtract(amount);
        this.setBalance(newBalance);
    }
}
