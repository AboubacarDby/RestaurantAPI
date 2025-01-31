package com.formation.restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.restaurants.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
