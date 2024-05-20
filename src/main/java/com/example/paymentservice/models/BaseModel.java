package com.example.paymentservice.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
/*
user ---> creates order using-->orderService

user ---> sends payment request to -->paymentService
createPaymentLink(orderId)

paymentService ---> talks to -->paymentGateway to create paymentLink
(DB) stores payment -->id,paymentStatus,userId,link etc
*/

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
}

