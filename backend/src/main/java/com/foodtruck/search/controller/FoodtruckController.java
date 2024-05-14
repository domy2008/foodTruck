/************************************************************************************
 * @author:  cong
 * @comment: this is the controller class for foodtruck, expose the restful api
 * 			  endpoint to the client
 *************************************************************************************/
package com.foodtruck.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodtruck.search.model.FoodCartPermit;
import com.foodtruck.search.service.ServiceDB;

@RestController
@RequestMapping("/api")
public class FoodtruckController {


	@Autowired
	private ServiceDB dServiceDB;

	@GetMapping("/foodtrucks")
	public String getAllFoodtrucks() {
		// Your code to retrieve all food trucks
		System.out.println("******  Yes, i was called *****");
		return "@getAllFoodtrucks";
	}
	@GetMapping("/getAllMatchedFood")
	public String getAllMatchedFood(@RequestParam String subItems) {
		// Your code to retrieve all food trucks

		List<FoodCartPermit> foodList = dServiceDB.getAllMatchedFood(subItems);

		// Convert foodList to JSON array
		ObjectMapper objectMapper = new ObjectMapper();
		String foodListJson = "";
		try {
			foodListJson = objectMapper.writeValueAsString(foodList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foodListJson;
	}

	//give code delete data
	
	@GetMapping("/foodtrucks/{id}")
	public String getFoodtruckById(@PathVariable Long id) {
		// Your code to retrieve a food truck by ID

	
		return "Food truck with ID: " + id;
	}

	@PostMapping("/foodtrucks")
	public String createFoodtruck(@RequestBody String foodtruck) {
		// Your code to create a new food truck
		return "Food truck created";
	}

	@PutMapping("/foodtrucks/{id}")
	public String updateFoodtruck(@PathVariable Long id, @RequestBody String foodtruck) {
		// Your code to update a food truck
		return "Food truck updated";
	}

	@DeleteMapping("/foodtrucks/{id}")
	public String deleteFoodtruck(@PathVariable Long id) {
		// Your code to delete a food truck
		return "Food truck deleted";
	}
}