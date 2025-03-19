package com.huyle.controller;

import java.util.List;

import com.huyle.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.stripe.exception.StripeException;
import com.huyle.model.Order;
import com.huyle.model.PaymentResponse;
import com.huyle.model.User;
import com.huyle.request.CreateOrderRequest;
import com.huyle.service.OrderService;
import com.huyle.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentService paymentService;

//	@PostMapping("/order")
//	public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request,
//											 @RequestHeader("Authorization") String jwt)
//			throws Exception {
//		User user = userService.findUserbyJwtToken(jwt);
//
//		Order order = orderService.createOrder(request, user);
//
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}

	@PostMapping("/order")
	public ResponseEntity<PaymentResponse>  createOrder(@RequestBody CreateOrderRequest request,
														@RequestHeader("Authorization") String jwt)
			throws Exception{
		User user=userService.findUserbyJwtToken(jwt);
		Order order = orderService.createOrder(request, user);
		PaymentResponse res = paymentService.generatePaymentLink(order);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@GetMapping("/order/user")
	public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserbyJwtToken(jwt);
		List<Order> orders = orderService.getUserOrders(user.getId());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
