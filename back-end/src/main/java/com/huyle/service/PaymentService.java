package com.huyle.service;

//import com.stripe.exception.StripeException;
import com.huyle.model.Order;
import com.huyle.model.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
