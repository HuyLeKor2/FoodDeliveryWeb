package com.huyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huyle.model.Cart;
import com.huyle.model.CartItem;
import com.huyle.model.User;
import com.huyle.request.AddCartItemRequest;
import com.huyle.request.UpdateCartItemRequest;
import com.huyle.service.CartService;
import com.huyle.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;

	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		CartItem cart = cartService.addItemToCart(req, jwt);
		return ResponseEntity.ok(cart);

	}

	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(
			@RequestBody UpdateCartItemRequest req, 
			@RequestHeader("Authorization") String jwt) throws Exception {
		CartItem cart = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		return ResponseEntity.ok(cart);
	}

	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{

		Cart cart = cartService.removeItemFromCart(id, jwt);
		return ResponseEntity.ok(cart);

	}

	@GetMapping("/cart/total")
	public ResponseEntity<Long> calculateCartTotals(@RequestParam Long cartId,
			@RequestHeader("Authorization") String jwt) throws Exception {

		
		User user = userService.findUserbyJwtToken(jwt);
		
		Cart cart =cartService.findCartByUserId(user.getId());
		Long total = cartService.calculateCartTotals(cart);
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/cart/")
	public ResponseEntity<Cart> findUserCart(
			@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserbyJwtToken(jwt);
		Cart cart = cartService.findCartByUserId(user.getId());
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> cleareCart(
			@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);
		Cart cart = cartService.clearCart(user.getId());
		return ResponseEntity.ok(cart);
	}

}
