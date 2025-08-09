package com.codewithmosh.store.payments;

import com.codewithmosh.store.orders.Order;
//import com.codewithmosh.store.payments.PaymentResult;
//import com.codewithmosh.store.payments.WebhookRequest;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
//    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);

    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
