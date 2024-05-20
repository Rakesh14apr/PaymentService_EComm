package com.example.paymentservice.services;

import com.example.paymentservice.models.Payment;
import com.example.paymentservice.models.PaymentGateway;
import com.example.paymentservice.models.PaymentStatus;
import com.example.paymentservice.paymentgateways.PaymentGatewayFactory;
import com.example.paymentservice.paymentgateways.PaymentGatewayInterface;
import com.example.paymentservice.paymentgateways.RazorpayPaymentGateway;
import com.example.paymentservice.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayFactory paymentGatewayFactory;
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentGatewayFactory paymentGatewayFactory,PaymentRepository paymentRepository){
        this.paymentGatewayFactory=paymentGatewayFactory;
        this.paymentRepository=paymentRepository;
    }

    public String createPaymentLink(Long orderId){
        // Need to get details of order specifically amount
        //Assume we have something like
        //Order order=restTemplate.getForObject("",Order.class);
        //Long amount=order.getAmount();
        //String userName=order.getUser().getName();
        //String userMobile=order.getUser().getPhoneNumber(); email etc..

        Long amount=1000L;
        String userName="Rakesh";
        String userEmail="rakesh@mail.com";
        String userMobile="+919999988888";


        PaymentGatewayInterface paymentGateway=paymentGatewayFactory.getBestPaymentGateway();

        String paymentLink=paymentGateway.createPaymentLink(
                amount,userName,userEmail,userMobile,orderId);

        Payment payment=new Payment();
        payment.setPaymentLink(paymentLink);
        payment.setOrderId(orderId);
        payment.setPaymentGateway(PaymentGateway.RAZORPAY);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setAmount(amount);

        paymentRepository.save(payment);

        return paymentLink;
    }

    public PaymentStatus getPaymentStatus(String paymentGatewayPaymentId){
        Payment payment=paymentRepository.findByPaymentGatewayReferenceId(paymentGatewayPaymentId);

        if(payment.getPaymentGateway().equals(PaymentGateway.RAZORPAY)){

        }else if(payment.getPaymentGateway().equals(PaymentGateway.PAYUMONEY)){

        }
        return null;
    }
}
