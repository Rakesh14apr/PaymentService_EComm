package com.example.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{

    private Long amount;
    private Long orderId;
    private Long userId;
    private PaymentStatus paymentStatus;
    private String paymentLink;
    private String paymentGatewayReferenceId;
    private PaymentGateway paymentGateway;
}
