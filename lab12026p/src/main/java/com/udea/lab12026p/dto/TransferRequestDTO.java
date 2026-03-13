package com.udea.lab12026p.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferRequestDTO {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Double amount;

    public TransferRequestDTO() {}
}
