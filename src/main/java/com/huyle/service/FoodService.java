package com.huyle.service;

import com.huyle.model.Category;
import com.huyle.model.Food;
import com.huyle.model.Restaurant;
import com.huyle.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegitarain,
                                        boolean isNonveg, boolean isSeasonal, String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    //kiểm tra coi hết hàng chưa => chuyển trạng thái sang off
    public Food updateAvailibityStatus(Long foodId) throws Exception;
}
