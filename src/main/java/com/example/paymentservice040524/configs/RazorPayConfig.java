package com.example.paymentservice040524.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RazorPayConfig {
    @Value("$(razorpay.key.id}")
    private String razorpayKeyId;

    @Value("$(razorpay.key.secret}")
    private String razorpaykeySecret;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient (razorpayKeyId,razorpaykeySecret);
    }
}
