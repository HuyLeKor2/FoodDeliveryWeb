package com.huyle.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyle.DTO.RestaurantDTO;
import com.huyle.model.Address;
import com.huyle.model.Restaurant;
import com.huyle.model.User;
import com.huyle.repository.AddressRepository;
import com.huyle.repository.RestaurantRepository;
import com.huyle.repository.UserRepository;
import com.huyle.request.CreateRestaurantRequest;
import com.huyle.service.RestaurantService;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(request.getRegistrationDate());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(updatedRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }

        if(updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }

        if(updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()) {
            throw new Exception("restaurant not found with id" + id);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws Exception {
        Restaurant restaurants = restaurantRepository.findByOwnerId(userId);
        if(restaurants == null) {
            throw new Exception("restaurant not found with OwnerId" + userId);
        }
        return restaurants;
    }

    @Override
    public RestaurantDTO addToFavorite(Long restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDTO dto = new RestaurantDTO();
        dto.setTitle(restaurant.getName());
        dto.setImg(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDes(restaurant.getDescription());

        boolean isChecked = false;
        List<RestaurantDTO> favorites = user.getFavorites();
        for(RestaurantDTO favorite : favorites) {
            if(favorite.getId().equals(restaurantId)) {
                isChecked = true;
                break;
            }
        }
        if (isChecked) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(dto);
        }
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
