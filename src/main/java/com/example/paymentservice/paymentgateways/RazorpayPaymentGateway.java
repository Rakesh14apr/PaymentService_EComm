package com.example.paymentservice.paymentgateways;

import com.example.paymentservice.models.PaymentStatus;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGatewayInterface{

    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient=razorpayClient;
    }

    //public RazorpayPaymentGateway(){}

    @Override
    public String createPaymentLink(Long amount, String userName, String userEmail, String userPhone,Long orderId)  {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis()/1000+ 30 * 60); //epoch time stamp
        paymentLinkRequest.put("reference_id",orderId.toString());
        //paymentLinkRequest.put("description","Payment for policy no #23456");

        JSONObject customer = new JSONObject();
        customer.put("name",userName);
        customer.put("contact",userPhone);
        customer.put("email",userEmail);
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);

        /*JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);*/

        paymentLinkRequest.put("callback_url","https://amazon.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment=null;

        try {
               payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        }catch(RazorpayException razorpayException){
            System.out.println(razorpayException);
            System.out.println("Something went wrong");
        }

        return payment.get("short_url");
    }

    @Override
    public PaymentStatus getPaymentStatus(String paymentId) {
        return null;
    }
}
