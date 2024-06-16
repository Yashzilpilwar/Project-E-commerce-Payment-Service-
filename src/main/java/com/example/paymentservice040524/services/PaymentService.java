package com.example.paymentservice040524.services;

import com.example.paymentservice040524.services.paymentgatways.PaymentGetways;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private PaymentGetways paymentGetways;

    PaymentService(@Qualifier("stripe") PaymentGetways paymentGetways) {
        this.paymentGetways = paymentGetways;
    }

    public String generatePaymentLink(String orderId,Long amount) throws StripeException , RazorpayException {
        return  paymentGetways.createPaymentLink(orderId,amount);
    }
}
