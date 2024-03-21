package com.formation.restaurants.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.restaurants.exception.RessourceNotFoundException;
import com.formation.restaurants.model.Dish;
import com.formation.restaurants.repository.DishRepository;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
	
	@Autowired
	private DishRepository dishRepository;
	
	@GetMapping
	public List<Dish> getAllDishes(){
		return dishRepository.findAll();	
	}
	
	@PostMapping
	public Dish createDish(@RequestBody Dish dish) {
		return dishRepository.save(dish);
	}
	
	@PutMapping
	public Dish uptadeDish(@PathVariable(value="id") long dishId, @RequestBody Dish dishDetails) {
		Dish dish = dishRepository.findById(dishId)
				.orElseThrow(()-> new RessourceNotFoundException("Dish not found for this id : " + dishId));
		dish.setName(dishDetails.getName());
		dish.setDescription(dishDetails.getDescription());
		dish.setPrice(dishDetails.getPrice());
		final Dish updatedDish = dishRepository.save(dish);
		return updatedDish;
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteDish(@PathVariable(value="id") long dishId){
		Dish dish = dishRepository.findById(dishId)
				.orElseThrow(()-> new RessourceNotFoundException("Dish not found for this id : " + dishId));
	dishRepository.delete(dish);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return response;
	}
}
