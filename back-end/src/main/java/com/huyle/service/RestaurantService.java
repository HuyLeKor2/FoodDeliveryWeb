package com.huyle.service;

import java.util.List;

import com.huyle.DTO.RestaurantDTO;
import com.huyle.model.Restaurant;
import com.huyle.model.User;
import com.huyle.request.CreateRestaurantRequest;

public interface RestaurantService  {
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant>getAllRestaurant();

    public List<Restaurant>searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantsByUserId(Long userId) throws Exception;

    public RestaurantDTO addToFavorite(Long restaurantId, User user) throws Exception;//?
 
    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
