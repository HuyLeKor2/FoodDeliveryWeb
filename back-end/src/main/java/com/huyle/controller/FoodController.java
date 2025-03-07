package com.huyle.controller;

import com.huyle.model.Food;
import com.huyle.model.Restaurant;
import com.huyle.model.User;
import com.huyle.repository.FoodRepository;
import com.huyle.request.CreateFoodRequest;
import com.huyle.response.MessageResponse;
import com.huyle.service.FoodService;
import com.huyle.service.RestaurantService;
import com.huyle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestBody String name,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user  = userService.findUserbyJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @PathVariable Long restaurantId,
            @RequestParam (required = false) boolean vegetarian,
            @RequestParam (required = false) boolean seasonal,
            @RequestParam (required = false) boolean nonveg,
            @RequestParam (required = false) String food_category,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user  = userService.findUserbyJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFood(restaurantId,vegetarian,nonveg,seasonal,food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
