package com.example.paymentservice.paymentgateways;

import com.example.paymentservice.models.PaymentStatus;

public class StripePaymentGateway implements  PaymentGatewayInterface{
    @Override
    public String createPaymentLink(Long amount, String userName, String userEmail, String userPhone,Long orderId) {

        return null;
    }

    @Override
    public PaymentStatus getPaymentStatus(String paymentId) {
        return null;
    }

   /* @Override
    public PaymentStatus getPaymentStatus(Long paymentId) {
        return null;
    }*/
}
