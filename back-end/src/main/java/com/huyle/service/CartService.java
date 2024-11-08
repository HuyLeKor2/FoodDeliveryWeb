package com.huyle.service;

import com.huyle.model.Cart;
import com.huyle.model.CartItem;
import com.huyle.model.Food;
import com.huyle.request.AddCartItemRequest;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quality) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws  Exception;

    public CartItem addNewCartItem(Food food, int quantity, List<String> ingredients, Long totalPrice, Cart cart);
}
