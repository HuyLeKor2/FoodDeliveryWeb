package com.huyle.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.stripe.exception.StripeException;
import com.huyle.model.Order;
import com.huyle.model.PaymentResponse;
import com.huyle.model.User;
import com.huyle.request.CreateOrderRequest;
import com.huyle.service.OrderService;
import com.huyle.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws Exception{
    	if(orderId!=null) {
    		orderService.cancelOrder(orderId);
    	return ResponseEntity.ok("Order deleted with id)"+orderId);
    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }
    
    
    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(
    		@PathVariable Long restaurantId,
    		@RequestParam(required = false) String order_status) throws Exception{
    		List<Order> orders = orderService.
    				getOrdersOfRestaurant(restaurantId,order_status);
    		
//    		System.out.println("ORDER STATUS----- "+orderStatus);
    		return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws Exception{
    		Order orders = orderService.updateOrder(orderId, orderStatus);
    		return ResponseEntity.ok(orders);
    		
    }

}
