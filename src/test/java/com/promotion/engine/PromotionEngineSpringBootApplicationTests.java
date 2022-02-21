package com.promotion.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.promotion.engine.model.*;
import com.promotion.engine.repository.*;
import com.promotion.engine.service.*;

@SpringBootTest
class PromotionEngineSpringBootApplicationTests {

	@Autowired
	IPriceService priceService;
	
	/*@MockBean
	IPromotionRepository promotionRepository;
	*/
	/**
	 * With JUNIT5 this annotation is added
	 * It runs once before each test cases
	 */ 
	@BeforeEach
	void beforeEach() {
		
	}
	
	
	/**
	 * No promotion is applied......
	 */
	@Test
	void should_return_100_when_A_B_C_Ordered_Once() {
		
		// Given
		
		Cart cart = new Cart(Arrays.asList(new ProductItem(new Product("A", 50, 0), 1),
				new ProductItem(new Product("B", 30, 0), 1),
				new ProductItem(new Product("C", 20, 0), 1)
				));
		
		List<Promotion >promotionList = new ArrayList<Promotion>();
		
		/*promotionList.add(new Promotion(Arrays.asList(new ProductItem(new Product("A", 50, 0), 3)), 120, false, 1));
		promotionList.add(new Promotion(Arrays.asList(new ProductItem(new Product("B", 50, 0), 2)), 45, false, 2));
		promotionList.add(new Promotion(Arrays.asList(
				new ProductItem(new Product("C", 20, 0), 1), 
				new ProductItem(new Product("D", 15, 0), 1)), 30, true, 3));
		
		when(promotionRepository.getPromotionList()).thenReturn(promotionList);
		*/
		double expectedValue = 100;
		
		// When
		
		double actualValue = this.priceService.calculatePromotedTotalPrice(cart);
		
		// Then
		
		assertEquals(expectedValue, actualValue);
		
	}
	
	@Test
	void should_return_370_when_5A_5B_1C_Ordered() {
		
		// Given
		
		Cart cart = new Cart(Arrays.asList(new ProductItem(new Product("A", 50, 0), 5),
				new ProductItem(new Product("B", 30, 0), 5),
				new ProductItem(new Product("C", 20, 0), 1)
				));
		
		double expectedValue = 370;
		
		// When
		
		double actualValue = this.priceService.calculatePromotedTotalPrice(cart);
		
		// Then
		
		assertEquals(expectedValue, actualValue);
		
	}
	
	@Test
	void should_return_280_when_3A_5B_1C_1D_Ordered() {
		
		// Given
		
		Cart cart = new Cart(Arrays.asList(new ProductItem(new Product("A", 50, 0), 3),
				new ProductItem(new Product("B", 30, 0), 5),
				new ProductItem(new Product("C", 20, 0), 1),
				new ProductItem(new Product("D", 15, 0), 1)
				));
		
		double expectedValue = 280;
		
		// When
		
		double actualValue = this.priceService.calculatePromotedTotalPrice(cart);
		
		// Then
		
		assertEquals(expectedValue, actualValue);
		
	}
	

	@Test
	void should_return_330_when_3A_5B_3C_2D_Ordered() {
		
		// Given
		
		Cart cart = new Cart(Arrays.asList(new ProductItem(new Product("A", 50, 0), 3),
				new ProductItem(new Product("B", 30, 0), 5),
				new ProductItem(new Product("C", 20, 0), 3),
				new ProductItem(new Product("D", 15, 0), 2)
				));
		
		double expectedValue = 330;
		
		// When
		
		double actualValue = this.priceService.calculatePromotedTotalPrice(cart);
		
		// Then
		
		assertEquals(expectedValue, actualValue);
		
	}

	@Test
	void should_return_352_when_3A_5B_3C_2D_Ordered_And_D_20_PercentDiscounted() {
		
		// Given
		
		Cart cart = new Cart(Arrays.asList(new ProductItem(new Product("A", 50, 0), 3),
				new ProductItem(new Product("B", 30, 0), 5),
				new ProductItem(new Product("C", 20, 0), 3),
				new ProductItem(new Product("D", 15, 20), 4)
				));
		
		double expectedValue = 352;
		
		// When
		
		double actualValue = this.priceService.calculatePromotedTotalPrice(cart);
		
		// Then
		
		assertEquals(expectedValue, actualValue);
		
	}


}
