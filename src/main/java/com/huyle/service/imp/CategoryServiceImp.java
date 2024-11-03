package com.huyle.service.imp;

import com.huyle.model.Category;
import com.huyle.model.Restaurant;
import com.huyle.repository.CategoryRepository;
import com.huyle.service.CategoryService;
import com.huyle.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImp implements CategoryService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name,Long userId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantsByUserId(userId);
        Category createdCategory=new Category();

        createdCategory.setName(name);
        createdCategory.setRestaurant(restaurant);
        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantsByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> opt=categoryRepository.findById(id);

        if(opt.isEmpty()) {
            throw new Exception("category not exist with id "+id);
        }

        return opt.get();
    }
}