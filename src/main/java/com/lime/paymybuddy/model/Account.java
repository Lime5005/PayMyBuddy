package com.lime.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Transaction> transactions;

    @Column(name = "balance")
    private BigDecimal balance;

}
