package com.example.paymentservice040524.controllers;


import com.example.paymentservice040524.dtos.InitiatePaymentDto;
import com.example.paymentservice040524.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> initiatePayment(@RequestBody InitiatePaymentDto requestDto) throws StripeException , RazorpayException {

        String paymentLink =  paymentService.generatePaymentLink(requestDto.getOrderId(), requestDto.getAmount());
        return ResponseEntity.ok(paymentLink);
    }
}
