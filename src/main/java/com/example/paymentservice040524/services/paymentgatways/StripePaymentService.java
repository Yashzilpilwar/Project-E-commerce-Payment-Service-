package com.example.paymentservice040524.services.paymentgatways;

import com.example.paymentservice040524.PaymentService040524Application;
import com.example.paymentservice040524.services.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("stripe")
public class StripePaymentService implements PaymentGetways {
    @Value("${stripe.api.secret}")
    private String stripeAPISecret;

    public String createPaymentLink(String orderId, Long amount) throws StripeException {

        Stripe.apiKey = stripeAPISecret;


        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("order Id: orderId").build()
                        )
                        .build();

        Price price = Price.create(priceParams);

        String paymentSuccessUrl = "https://yash.in";
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                          PaymentLinkCreateParams.AfterCompletion.builder()
                                  .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                  .setRedirect(
                                          PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                  .setUrl(paymentSuccessUrl)
                                                  .build()
                                  )
                                  .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        System.out.println(paymentLink.getUrl());
        return paymentLink.getUrl();

    }


}
