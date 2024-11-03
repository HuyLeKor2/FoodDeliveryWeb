package com.huyle.controller;

import com.huyle.DTO.RestaurantDTO;
import com.huyle.model.Restaurant;
import com.huyle.model.User;
import com.huyle.service.RestaurantService;
import com.huyle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/restaurants")
@RestController
public class RestaurantController {
    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    public UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> findRestaurantByName(
            @RequestHeader("Authorization") String jwt,//? cái này vô làm gì
            @RequestParam String keyword
    ) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Long id
    ) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDTO> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.addToFavorite(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
