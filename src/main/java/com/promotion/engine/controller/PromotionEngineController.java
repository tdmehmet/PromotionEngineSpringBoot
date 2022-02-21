package com.promotion.engine.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.promotion.engine.model.Cart;
import com.promotion.engine.service.IPriceService;

@RestController
public class PromotionEngineController {

	@Autowired
	IPriceService priceService;

	@PostMapping(value = "/calculatePromotedTotalPrice", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public double calculatePromotedTotalPrice(@RequestBody Cart cart) {
		
		double returnVal = priceService.calculatePromotedTotalPrice(cart);
		
		return returnVal;
		
	}
		
	
}
