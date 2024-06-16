package com.example.paymentservice040524.services.paymentgatways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGetways {

    public String createPaymentLink(String orderId, Long amount) throws StripeException, RazorpayException;;
}
