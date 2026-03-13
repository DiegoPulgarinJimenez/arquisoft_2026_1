package com.udea.lab12026p.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false,  length = 50)
    private String lastName;

    @Column(nullable = false)
    private Double amount;

    public Customer() {
    }

    public Customer(@JsonProperty("id") Long id,
                    @JsonProperty("accountNumber") String accountNumber,
                    @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("amount") Double amount) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.id = id;
    }

}
