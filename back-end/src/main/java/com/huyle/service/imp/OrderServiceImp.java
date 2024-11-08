package com.huyle.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyle.model.Address;
import com.huyle.model.Cart;
import com.huyle.model.CartItem;
import com.huyle.model.Order;
import com.huyle.model.OrderItem;
import com.huyle.model.Restaurant;
import com.huyle.model.User;
import com.huyle.repository.AddressRepository;
import com.huyle.repository.OrderItemRepository;
import com.huyle.repository.OrderRepository;
import com.huyle.repository.RestaurantRepository;
import com.huyle.repository.UserRepository;
import com.huyle.request.CreateOrderRequest;
import com.huyle.service.CartService;
import com.huyle.service.OrderService;
@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private UserRepository userRepository;
	@Override
	public Order createOrder(CreateOrderRequest order,User user) throws Exception{

		Address shippAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippAddress);
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
		}
		System.out.println("user addresses --------------  "+user.getAddresses());
		userRepository.save(user);


		Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
		if(restaurant.isEmpty()) {
			throw new Exception("Restaurant not found with id "+order.getRestaurantId());
		}

		Order createdOrder = new Order();

		createdOrder.setCustomer(user);
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setRestaurant(restaurant.get());

		Cart cart = cartService.findCartByUserId(user.getId());

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getFood().getPrice()* cartItem.getQuantity());
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}

		Long totalPrice = cartService.calculateCartTotals(cart);

		createdOrder.setTotalAmount(totalPrice);
		createdOrder.setRestaurant(restaurant.get());

		createdOrder.setItems(orderItems);
		Order savedOrder = orderRepository.save(createdOrder);

		restaurant.get().getOrders().add(savedOrder);

		restaurantRepository.save(restaurant.get());
		return createdOrder;
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order =findOrderById(orderId);
		if(order==null) {
			throw new Exception("Order not found with the id "+orderId);
		}

		orderRepository.deleteById(orderId);

	}

	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) return order.get();

		throw new Exception("Order not found with the id "+orderId);
	}

	@Override
	public List<Order> getUserOrders(Long userId) throws Exception {
		List<Order> orders=orderRepository.findAllUserOrders(userId);
		return orders;
	}

	@Override
	public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws Exception {

		List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);

		if(orderStatus!=null) {
			orders = orders.stream()
					.filter(order->order.getOrderStatus().equals(orderStatus))
					.collect(Collectors.toList());
		}

		return orders;
	}
//    private List<MenuItem> filterByVegetarian(List<MenuItem> menuItems, boolean isVegetarian) {
//    return menuItems.stream()
//            .filter(menuItem -> menuItem.isVegetarian() == isVegetarian)
//            .collect(Collectors.toList());
//}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order=findOrderById(orderId);
		System.out.println("--------- "+orderStatus);
		if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
				|| orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		else throw new Exception("Please Select A Valid Order Status");
	}
}
