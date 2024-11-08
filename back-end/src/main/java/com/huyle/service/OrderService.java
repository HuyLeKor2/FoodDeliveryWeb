package com.huyle.service;

import java.util.List;

//import com.stripe.exception.StripeException;
import com.huyle.model.Order;
import com.huyle.model.PaymentResponse;
import com.huyle.model.User;
import com.huyle.request.CreateOrderRequest;

public interface OrderService {
	
	 public Order createOrder(CreateOrderRequest order, User user) throws Exception;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	 
	 public void cancelOrder(Long orderId) throws Exception;
	 
	 public List<Order> getUserOrders(Long userId) throws Exception;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws Exception;
	 

}
