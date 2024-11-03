package com.huyle.service.imp;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

import com.huyle.service.IngredientsService;
import com.huyle.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huyle.model.IngredientCategory;
import com.huyle.model.IngredientsItem;
import com.huyle.model.Restaurant;
import com.huyle.repository.IngredientsCategoryRepository;
import com.huyle.repository.IngredientsItemRepository;

@Service
public class IngredientsServiceImplementation implements IngredientsService {

	@Autowired
	private IngredientsCategoryRepository ingredientsCategoryRepository;

	@Autowired
	private IngredientsItemRepository ingredientsItemRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientsCategory(
			String name,Long restaurantId) throws Exception {

		IngredientCategory isExist=ingredientsCategoryRepository
				.findByRestaurantIdAndNameIgnoreCase(restaurantId,name);

		if(isExist!=null) {
			return isExist;
		}

		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

		IngredientCategory ingredientCategory=new IngredientCategory();
		ingredientCategory.setRestaurant(restaurant);
		ingredientCategory.setName(name);

		IngredientCategory createdCategory = ingredientsCategoryRepository.save(ingredientCategory);

		return createdCategory;
	}

	@Override
	public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> opt=ingredientsCategoryRepository.findById(id);
		if(opt.isEmpty()){
			throw new Exception("ingredient category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		return ingredientsCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {

		return ingredientsItemRepository.findByRestaurantId(restaurantId);
	}


	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId,
												 String ingredientName, Long ingredientCategoryId) throws Exception {

		IngredientCategory category = findIngredientsCategoryById(ingredientCategoryId);

		IngredientsItem isExist = ingredientsItemRepository.
				findByRestaurantIdAndNameIngoreCase(restaurantId, ingredientName,category.getName());
		if(isExist!=null) {
			System.out.println("is exists-------- item");
			return isExist;
		}

		Restaurant restaurant=restaurantService.findRestaurantById(
				restaurantId);
		IngredientsItem item=new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);

		IngredientsItem savedIngredients = ingredientsItemRepository.save(item);
		category.getIngredients().add(savedIngredients);

		return savedIngredients;
	}


	@Override
	public IngredientsItem updateStoke(Long id) throws Exception {
		Optional<IngredientsItem> item=ingredientsItemRepository.findById(id);
		if(item.isEmpty()) {
			throw new Exception("ingredient not found with id "+item);
		}
		IngredientsItem ingredient=item.get();
		ingredient.setInStoke(!ingredient.isInStoke());
		return ingredientsItemRepository.save(ingredient);
	}
}
