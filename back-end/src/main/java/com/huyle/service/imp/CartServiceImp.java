package com.huyle.service.imp;

import com.huyle.model.Cart;
import com.huyle.model.CartItem;
import com.huyle.model.Food;
import com.huyle.model.User;
import com.huyle.repository.CartItemRepository;
import com.huyle.repository.CartRepository;
import com.huyle.repository.FoodRepository;
import com.huyle.request.AddCartItemRequest;
import com.huyle.service.CartService;
import com.huyle.service.FoodService;
import com.huyle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImp.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        Optional<Food> food = foodRepository.findById(req.getFoodId());
        if(food.isEmpty()) {
            throw new Exception("Menu Item not exist with id "+req.getFoodId());
        }
        Cart cart = findCartByUserId(user.getId());

        for (CartItem cartItem : cart.getItems()) {//? coi lại khúc này ( chỉ là check coi đã có hàng, nếu có
            // thì thêm vào số lương + 1, không thì thêm vào cartItem
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food.get());
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.get().getPrice());

        CartItem savedItem=cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;


    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quality) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        if(cartItemOptional.isEmpty()) {
            throw new Exception("cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quality);

        //5*100 = 500

        item.setTotalPrice(item.getFood().getPrice()*quality);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);
        Cart cart = findCartByUserId(user.getId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()) {
            throw new Exception("cart item not found");
        }
        CartItem item = cartItemOptional.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem cartItem: cart.getItems()) {
            total += cartItem.getQuantity()* cartItem.getFood().getPrice();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);

        if(cart.isEmpty()) {
            throw new Exception("cart not found with id "+ id);
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Optional<Cart> opt=cartRepository.findByCustomerId(userId);
		if(opt.isPresent()) {
			return opt.get();
		}
        throw new Exception("cart not found");
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }


}
