package com.udea.lab12026p.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private Double amount;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id,
                       String firstName,
                       String lastName,
                       String accountNumber,
                       Double amount) {

    }
}
